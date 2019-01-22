package com.lizp.sec.cache.service;

import com.lizp.sec.cache.util.Result;

public interface StockService {

	public Result<Integer> subStock(Long skId, Integer decrNum);

	public void throwsException();
}
