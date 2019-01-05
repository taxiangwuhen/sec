package com.lizp.sec.cache.dao;

import org.springframework.stereotype.Repository;

import com.lizp.sec.cache.entity.Item;

@Repository
public interface ItemDao {

	public Item getById(Long id);
}
