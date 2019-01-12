package com.lizp.sec.cache.config;

public class ItemPrefixKey extends BasePrefixKey{
	
	


	public ItemPrefixKey(String prefix) {
		super(prefix);
	}

	public static ItemPrefixKey getItemTagById = new ItemPrefixKey("getItemTagById:");
	public static ItemPrefixKey saveItemTagById = new ItemPrefixKey("saveItemTagById:");
	
	/** 秒杀活动库存 **/
	public static String PROM_SK_STOCK = "promSkStock:{skId}";
	
	public static final String PROM_SK_STOCK_LOAD_LOCK = "promSkStock:{skId}";
	public static final String PROM_SK_STOCK_LOAD_LOCK_EXPIRE = null;
	
}
