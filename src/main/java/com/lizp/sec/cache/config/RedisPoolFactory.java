package com.lizp.sec.cache.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {

	@Autowired
	private RedisConfig jedisConfig;
	
	@Bean
	public JedisPool jedisPoolFactory() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(jedisConfig.getPoolMaxTotal());
		poolConfig.setMaxIdle(jedisConfig.getPoolMaxIdle());
		poolConfig.setMaxWaitMillis(jedisConfig.getPoolMaxWait() *1000);
		
		JedisPool jedisPool = new JedisPool(poolConfig, 
				jedisConfig.getHost(), 
				jedisConfig.getPort(),
				jedisConfig.getTimeout() *1000,
				jedisConfig.getPassword(),
				2);
		
		return jedisPool;
	}
	
	
}
