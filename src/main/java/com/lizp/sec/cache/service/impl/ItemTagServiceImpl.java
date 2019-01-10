package com.lizp.sec.cache.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lizp.sec.cache.dao.ItemTagDao;
import com.lizp.sec.cache.entity.ItemTag;
import com.lizp.sec.cache.service.ItemTagService;

@Service
public class ItemTagServiceImpl implements ItemTagService {
	
  @Autowired	
  private ItemTagDao itemTagDao;
	
	@Override
	@Transactional
	public ItemTag save(ItemTag it) {
		try {
			itemTagDao.save(it);
			
			// 事务测试
			ItemTag b = new ItemTag();
			b.setId(8l);
			b.setType(1);
			b.setState(1);
			b.setName("一定成功");
			itemTagDao.save(b);
		}catch (Exception e) {
			throw new RuntimeException();
		}
		return it;
	}

}
