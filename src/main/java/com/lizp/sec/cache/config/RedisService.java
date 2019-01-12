package com.lizp.sec.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisService {

	@Autowired
	private JedisPool jedisPool;
	
	/**
	 * 设置缓存
	 * @param key
	 * @param t
	 * @return
	 */
	public <T> boolean set(PrefixKey preKey, String key, T t) {
		Jedis jedis = null;
			try{
				jedis = jedisPool.getResource();
				
				String preKeyVal = preKey.getPreKey();
				
				// 将实体转化成json
				String json = beanToStr(t);
				
				// 异常判断
				if(StringUtils.isEmpty(preKeyVal+key) || null == t){
					return false;
				}
				
				jedis.set(preKeyVal+key, json);
				
				return true;
			}catch (Exception e) {
				return false;
			}
			finally {
				returnToPool(jedis);
			}
	}
	
	/**
	 * 获取缓存
	 * @param <T>
	 * @param key
	 */
	public <T> T get(PrefixKey preKey,String key, Class<T> clazz) {
		Jedis jedis = null;
			try{
				jedis = jedisPool.getResource();
		
				String preKeyVal = preKey.getPreKey();
				String val= jedis.get(preKeyVal+key);
				
				// 将实体转化成json
				T obj = strToBean(val, clazz);
				
				return obj;
			}catch (Exception e) {
				return null;
			}
			finally {
				returnToPool(jedis);
			}
	}
	
	/**
	 * 判断key是否存在
	 * @param <T>
	 * @param key
	 */
	public boolean exist(PrefixKey preKey,String key) {
		Jedis jedis = null;
			try{
				jedis = jedisPool.getResource();
				
				String preKeyVal = preKey.getPreKey();
		
				boolean val = jedis.exists(preKeyVal+key);
				
				return val;
			}catch (Exception e) {
				return false;
			}
			finally {
				returnToPool(jedis);
			}
	}

	// 将实体类转化成字符串，兼容Integer、Long、String、T
	private <T> String beanToStr(T t) {
		if(null == t) return null;
		
		Class<?> clazz = t.getClass();
		if(clazz == Integer.class || clazz == int.class) {
			return ""+t;
		}else if(clazz == Long.class || clazz == Long.class) {
			return ""+t;
		}else if(clazz == String.class) {
			return (String) t;
		}else {
			return JSON.toJSONString(t);
		}
	}
	
	// 将成字符串转化实体类，兼容Integer、Long、String、T
	private <T> T strToBean(String val, Class<T> clazz) {
		if(null == val) return null;
		
		if(clazz == Integer.class || clazz == int.class) {
			return (T) Integer.valueOf(val);
		}else if(clazz == Long.class || clazz == long.class) {
			return (T) Long.valueOf(val);
		}else if(clazz == String.class) {
			return (T) String.valueOf(val);
		}else {
			//return JSON.parseObject(val, clazz);
			return JSON.toJavaObject(JSON.parseObject(val), clazz);
		}
	}
	
	private void returnToPool(Jedis jedis) {
		 if(jedis != null) {
			 jedis.close();
		 }
	}
	
	
}
