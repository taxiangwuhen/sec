package com.lizp.sec.cache.config;

public class UserPrefixKey extends BasePrefixKey{
	
	


	public UserPrefixKey(String prefix) {
		super(prefix);
	}
	
	public UserPrefixKey(int expireSeconds,  String prefix) {
		super(expireSeconds,prefix);
	}

	
	/** session有效期 **/
	public static final int PROM_SK_STOCK_LOAD_LOCK_EXPIRE = 30*60;
	
	public static UserPrefixKey sessionId = new UserPrefixKey(PROM_SK_STOCK_LOAD_LOCK_EXPIRE, "sessionId:");


	
	
}
