package com.lizp.sec.cache.service.impl;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lizp.sec.cache.config.ItemPrefixKey;
import com.lizp.sec.cache.config.RedisService;
import com.lizp.sec.cache.exception.GlobalException;
import com.lizp.sec.cache.service.StockService;
import com.lizp.sec.cache.util.CodeMsg;
import com.lizp.sec.cache.util.Result;


@Service
public class StockServiceImpl implements StockService {
	 Logger logger = LoggerFactory.getLogger(StockServiceImpl.class);
	
	  @Autowired	
	  private RedisService redisService;
	  
	  public static final int LOW_STOCK = 0;// * 库存不足
	  public static final long UNINITIALIZED_STOCK = -1L;// * 不限库
	  
	  public static final String DECR_SK_STOCK_LUA;// 执行扣库存的脚本
	    static {
	        /**
	         * 扣减秒杀活动库存Lua脚本
	         * @param KEYS 活动库存key
	         * @param ARGV 扣减数
	         * @return
	         *      0:库存不足
	         *      -1:库存未初始化
	         *      大于0:剩余库存（扣减之前剩余的库存）
	         */
	        StringBuilder sb = new StringBuilder();
	        sb.append("if (redis.call('exists', KEYS[1]) == 1) then");
	        sb.append("    local stock = tonumber(redis.call('get', KEYS[1]));");
	        sb.append("    if (stock > 0) then");
	        sb.append("        local decrNum = tonumber(ARGV[1]);");
	        sb.append("        if (decrNum > stock) then");
	        sb.append("            return 0;");
	        sb.append("        end;");
	        sb.append("        redis.call('decrby', KEYS[1], decrNum);");
	        sb.append("        return stock;");
	        sb.append("    end;");
	        sb.append("    return 0;");
	        sb.append("end;");
	        sb.append("return -1;");
	        DECR_SK_STOCK_LUA = sb.toString();
	    }

	    
	public Result<Integer> subStock(Long skId, Integer decrNum) {
		 
		 Integer result = decrSkStockInRedis(skId, decrNum);
		 logger.info("缓存中命中"+result);
	        if (result == -1) {//未初始化库存
	            try {
	                //初始化redis库存
	                waitUtilLockLoadSkStock(skId);//上锁
	                
	                result = decrSkStockInRedis(skId, decrNum);// 双重验证，避免并发时重复回源到数据库
	                if (result == -1) {
	                    Integer stock = 10;
	                    logger.info("数据库查询"+stock);
	                    String cacheKey = ItemPrefixKey.PROM_SK_STOCK.replace("{skId}", skId.toString());
	                    redisService.set(ItemPrefixKey.getItemTagById, cacheKey, stock.toString());

	                    result = decrSkStockInRedis(skId, decrNum);
	                }
	            } catch (Exception e) {
	                logger.error(e.getMessage(), e);
	            } finally {
	                unLockLoadSkStock(skId);//释放锁
	            }
	        }
	        if (result > 0) {//扣减成功
	            return Result.succ(result);
	        } else {//库存不足
	            return Result.fail(CodeMsg.fail);// 库存不足
	        }
	}
	
    /**
     * redis中尝试扣减活动库存
     * @param skId
     * @param decrNum
     * @return -1:未初始化redis库存，0：库存不足， >0：剩余库存（扣减之前剩余的库存）
     */
    private Integer decrSkStockInRedis(Long skId, Integer decrNum) {
        String cacheKey = ItemPrefixKey.PROM_SK_STOCK.replace("{skId}", skId.toString());
        List<String> keys = Arrays.asList("ItemPrefixKey.getItemTagById:"+cacheKey);
        List<String> args = Arrays.asList(decrNum.toString());
        Long ret = (Long) redisService.eval(DECR_SK_STOCK_LUA, keys, args);
        return ret.intValue();
    }
    
    //尝试加锁，直到成功
    private void waitUtilLockLoadSkStock(Long skId) {
        while (!lockLoadSkStock(skId)) {//未上锁成功，50ms后再试
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
            	logger.error(e.getMessage(), e);
                Thread.currentThread().interrupt();
            }
        }
    }

    //加载活动库存并发锁加锁
    private boolean lockLoadSkStock(Long skId) {
        String cacheKey = ItemPrefixKey.PROM_SK_STOCK_LOAD_LOCK.replace("{skId}", skId.toString());
        return redisService.set(ItemPrefixKey.getItemTagById, cacheKey+"locked", 1);
    }
    //加载活动库存并发锁解锁
    private void unLockLoadSkStock(Long skId) {
        String cacheKey = ItemPrefixKey.PROM_SK_STOCK_LOAD_LOCK.replace("{skId}", skId.toString());
        redisService.delete(ItemPrefixKey.getItemTagById,cacheKey+"locked");
    }
    
    public void throwsException() {
    	try {
    		Thread.sleep(200);
    		String[] args = "".split(",");
    		System.out.println("args[1]="+args[1]);
    	}catch (Exception e) {
			throw new GlobalException(CodeMsg.exception.fillArg(e));
		}
    	
    	
    }

	
}
