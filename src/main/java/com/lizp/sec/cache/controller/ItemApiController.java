package com.lizp.sec.cache.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lizp.sec.cache.config.ItemPrefixKey;
import com.lizp.sec.cache.config.RedisService;
import com.lizp.sec.cache.entity.Item;
import com.lizp.sec.cache.entity.ItemTag;
import com.lizp.sec.cache.service.ItemService;
import com.lizp.sec.cache.service.ItemTagService;
import com.lizp.sec.cache.service.StockService;
import com.lizp.sec.cache.util.CodeMsg;
import com.lizp.sec.cache.util.Result;
import com.lizp.sec.cache.util.UUIDUtil;
import com.lizp.sec.cache.vo.LoginVo;


@RestController
@RequestMapping("/item/api")
public class ItemApiController {

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ItemTagService itemTagService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private RedisService redisService;
	
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
	
	@RequestMapping("/jedis/{id}")
	public Result<ItemTag> jedis(@PathVariable Long id) {
		ItemTag a = new ItemTag();
		a.setId(id);
		a.setType(1);
		a.setState(1);
		a.setName("蜜淘日志");
		redisService.set(ItemPrefixKey.getItemTagById, id.toString(), a);
		
		
		//ItemTag b = redisService.get("xxx", a.getClass());
		
		//System.out.println(JSON.toJSONString(b));
		
		return Result.succ(a);
	} 
	
	@RequestMapping("/lock/{id}")
	public Result<Integer> lock(@PathVariable Long id) {
		return stockService.subStock(id, 1);
	} 
	
	@GetMapping("/login/{mobile}")
	public Result<Boolean> login(HttpServletRequest req, 
			HttpServletResponse response,
			@PathVariable String mobile) {
		//stockService.throwsException();
		LoginVo login = new LoginVo();
		login.setMobile(mobile);
		addCookie(req, response, login);
		

		return Result.succ(true);
	} 
	

	private void addCookie(HttpServletRequest req, HttpServletResponse response, LoginVo login) {
		String randomVal = UUIDUtil.getUUIDString();
		Cookie cookie = new Cookie("token", randomVal);
		cookie.setPath("/");
		cookie.setMaxAge(60*5);// 5分钟
		response.addCookie(cookie);
		
		stockService.setCook(randomVal, login);
	}

	@GetMapping("/cookie/get")
	public Result<Boolean> cookie(LoginVo login) {
		System.out.println("111="+JSONObject.toJSONString(login));
		return Result.succ(true);
	} 
	
}
