package com.lizp.sec.cache.util;

public class Result<T> {

	private Integer code;// -1异常，0错误，1正常
	private String msg;// 消息
	private T data;// 数据
	
	
	public static <T> Result fail(CodeMsg msg) {
		return new Result<T>(msg, null);
	}
	
	public static <T> Result succ(T data) {
		return new Result<T>(CodeMsg.succ, data);
	}
	
	private Result(CodeMsg cm ,T data) {
		if(null == cm) {
			return;
		}
		
		this.code = cm.getCode();
		this.msg = cm.getMsg();
		this.data = data;
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
