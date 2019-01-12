package com.lizp.sec.cache.config;

public class ItemPrefixKey extends BasePrefixKey{
	
	
	public ItemPrefixKey(String prefix) {
		super(prefix);
	}

	public static ItemPrefixKey getItemTagById = new ItemPrefixKey("getItemTagById:");
	public static ItemPrefixKey saveItemTagById = new ItemPrefixKey("saveItemTagById:");
	
}
