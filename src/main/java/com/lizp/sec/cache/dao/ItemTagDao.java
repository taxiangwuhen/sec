package com.lizp.sec.cache.dao;

import org.springframework.stereotype.Repository;

import com.lizp.sec.cache.entity.ItemTag;

@Repository
public interface ItemTagDao {

	public void save(ItemTag it);
}
