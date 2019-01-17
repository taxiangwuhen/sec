package com.lizp.sec.cache.config;

public class ItemPrefixKey extends BasePrefixKey{
	
	


	public ItemPrefixKey(String prefix) {
		super(prefix);
	}
	
	public ItemPrefixKey(int expireSeconds,  String prefix) {
		super(expireSeconds,prefix);
	}

	
	/** 秒杀活动库存 **/
	public static String PROM_SK_STOCK = "promSkStock:{skId}";
	
	public static final String PROM_SK_STOCK_LOAD_LOCK = "promSkStock:{skId}";
	/** 秒杀活动库存加载锁缓存有效期 **/
	public static final int PROM_SK_STOCK_LOAD_LOCK_EXPIRE = 30;
	
	public static ItemPrefixKey getItemTagById = new ItemPrefixKey(PROM_SK_STOCK_LOAD_LOCK_EXPIRE, "getItemTagById:");
	public static ItemPrefixKey saveItemTagById = new ItemPrefixKey("saveItemTagById:");


	
	
}
