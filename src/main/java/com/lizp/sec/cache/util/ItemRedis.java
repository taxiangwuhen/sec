package com.lizp.sec.cache.util;

import com.lizp.sec.cache.config.BasePrefixKey;

public class ItemRedis extends BasePrefixKey{
	
	
	public ItemRedis(String prefix) {
		super(prefix);
	}

	public static ItemRedis getItemTagById = new ItemRedis("getItemTagById:");
	public static ItemRedis saveItemTagById = new ItemRedis("saveItemTagById:");
	
}
