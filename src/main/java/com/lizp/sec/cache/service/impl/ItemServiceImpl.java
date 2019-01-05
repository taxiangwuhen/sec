package com.lizp.sec.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.lizp.sec.cache.dao.ItemDao;
import com.lizp.sec.cache.entity.Item;
import com.lizp.sec.cache.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
  @Autowired	
  private ItemDao itemDao;
	
	@Override
	@Cacheable(value="item", key="#id")// item::1234
	public Item getById(Long id) {
		return itemDao.getById(id);
	}

}
