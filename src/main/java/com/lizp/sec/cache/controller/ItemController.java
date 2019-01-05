package com.lizp.sec.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lizp.sec.cache.entity.Item;
import com.lizp.sec.cache.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/{id}")
	public ModelAndView getItem(@PathVariable Long id) {
		System.out.println("id="+id);
		ModelAndView res = new ModelAndView("item/detail");
		Item item = itemService.getById(id);
		res.addObject("item", item);
		return res;
		
	} 
	
}
