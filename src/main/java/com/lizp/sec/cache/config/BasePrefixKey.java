package com.lizp.sec.cache.config;

public abstract class BasePrefixKey implements PrefixKey{
	
	
	private String prefix;
	private int expireSeconds;
	

	public BasePrefixKey(int expireSeconds, String prefix) {
		this.prefix = prefix;
		this.expireSeconds= expireSeconds;
	}
	
	public BasePrefixKey(String prefix) {
		this(0, prefix);
	}

	public int getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(int expireSeconds) {
		this.expireSeconds = expireSeconds;
	}

	public String getPreKey() {
		return getClass().getSimpleName()+"."+prefix;
	}

	
}
