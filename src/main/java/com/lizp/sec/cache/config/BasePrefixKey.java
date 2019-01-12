package com.lizp.sec.cache.config;

public abstract class BasePrefixKey implements PrefixKey{
	
	private String prefix;
	

	public BasePrefixKey() {
		super();
	}
	
	public BasePrefixKey(String prefix) {
		this.prefix = prefix; 
	}




	public String getPreKey() {
		return getClass().getSimpleName()+"."+prefix;
	}
}
