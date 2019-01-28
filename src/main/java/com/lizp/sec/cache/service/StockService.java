package com.lizp.sec.cache.service;

import com.lizp.sec.cache.util.Result;
import com.lizp.sec.cache.vo.LoginVo;

public interface StockService {

	public Result<Integer> subStock(Long skId, Integer decrNum);

	public void throwsException();

	public Object getCook(String val);

	public void setCook(String randomVal, LoginVo login);
}
