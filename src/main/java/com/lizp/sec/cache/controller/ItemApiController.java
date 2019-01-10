package com.lizp.sec.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizp.sec.cache.entity.Item;
import com.lizp.sec.cache.entity.ItemTag;
import com.lizp.sec.cache.service.ItemService;
import com.lizp.sec.cache.service.ItemTagService;
import com.lizp.sec.cache.util.CodeMsg;
import com.lizp.sec.cache.util.Result;


@RestController
@RequestMapping("/item/api")
public class ItemApiController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemTagService itemTagService;
	
	@RequestMapping("/{id}")
	public Result<Item> getItem(@PathVariable Long id) {
		System.out.println("id="+id);
		Item item = itemService.getById(id);
		return Result.succ(item);
	} 
	
	@RequestMapping("/error/{id}")
	public Result<Item> getErrorItem(@PathVariable Long id) {
		System.out.println("id="+id);
		Item item = itemService.getById(id);
		return Result.fail(CodeMsg.fail);
	} 
	
	@RequestMapping("/save/{id}")
	public Result<ItemTag> save(@PathVariable Long id) {
		ItemTag a = new ItemTag();
		a.setId(10l);
		a.setType(1);
		a.setState(1);
		a.setName("蜜淘日志");
		itemTagService.save(a);
		
		return Result.fail(CodeMsg.fail);
	} 
	
}
