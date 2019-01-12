package com.lizp.sec.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@MapperScan("com.lizp.sec.cache.dao") //扫描的mapper
//@EnableCaching
public class SecCacheApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecCacheApplication.class, args);
	}
}
