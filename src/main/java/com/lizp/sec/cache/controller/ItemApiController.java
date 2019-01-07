package com.lizp.sec.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lizp.sec.cache.entity.Item;
import com.lizp.sec.cache.service.ItemService;
import com.lizp.sec.cache.util.CodeMsg;
import com.lizp.sec.cache.util.Result;


@RestController
@RequestMapping("/item/api")
public class ItemApiController {

	@Autowired
	private ItemService itemService;
	
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
	
}
