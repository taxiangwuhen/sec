package com.lizp.sec.cache.util;

import org.springframework.util.DigestUtils;


public class MD5Util{
   private static String salt ="mimanicai";
	
	public static String pass2Md5(String inputPass) {
		return DigestUtils.md5DigestAsHex(inputPass.getBytes());
	}
	
	public static String pass2Md5WithSalt(String inputPass, String salt) {// 数据库保存时，应把salt和md5的结果都保存下来
		String pass = salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+inputPass+salt.charAt(4);
		return DigestUtils.md5DigestAsHex(pass.getBytes());
	}
	
	
	public static void main(String[] args) {
		System.out.println("123456="+pass2Md5("123456"));
		
		System.out.println("123456="+pass2Md5WithSalt("123456", salt));
	}
}
