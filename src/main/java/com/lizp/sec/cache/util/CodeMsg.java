package com.lizp.sec.cache.util;

public class CodeMsg {

	private Integer code;// -1异常，0错误，1正常
	private String msg;// 消息
	
	
	public static CodeMsg succ = new CodeMsg(1, "操作成功");
	public static CodeMsg fail = new CodeMsg(0, "操作失败");
	public static CodeMsg exception = new CodeMsg(-1, "操作异常:%s");
	
	private CodeMsg(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
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
	
	public CodeMsg fillArg(Object... arg){
		int code = this.code;
		String mString = String.format(msg, arg);
		return new CodeMsg(code, mString);
	}
	
	
}
