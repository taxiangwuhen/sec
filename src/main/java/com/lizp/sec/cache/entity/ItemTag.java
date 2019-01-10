package com.lizp.sec.cache.entity;

import java.io.Serializable;

public class ItemTag implements Serializable{
   private Long id;// id
   private Integer type;// 类型：1 标签
   private Integer state;// 状态：0停用  1启用
   private String name;// 标签名称
   private String thumb;// 图片
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	   
}
