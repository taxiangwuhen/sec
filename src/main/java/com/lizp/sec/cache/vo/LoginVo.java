package com.lizp.sec.cache.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.lizp.sec.cache.validator.IsMobile;

public class LoginVo {
	
	@NotNull
	@IsMobile(required=true)
	private String mobile;
	
	@NotNull
	@Length(min=6)
	private String password;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginVo [mobile=" + mobile + ", password=" + password + "]";
	}
}
