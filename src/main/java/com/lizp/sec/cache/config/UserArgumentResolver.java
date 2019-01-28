package com.lizp.sec.cache.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolverComposite;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.lizp.sec.cache.service.StockService;
import com.lizp.sec.cache.vo.LoginVo;

@Service
public class UserArgumentResolver extends HandlerMethodArgumentResolverComposite{
   
	@Autowired
	private StockService StockService;
	
	// 配置的类型
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> clazz = parameter.getParameterType();
		return clazz == LoginVo.class;
	}
	
	
	// 转化
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = webRequest.getNativeRequest(HttpServletResponse.class);
		
		String parval = request.getParameter("token");// 判断参数传递
		String cookval = getCookie(request, "token");// 判断cookie传递
		
		if(StringUtils.isEmpty(parval) && StringUtils.isEmpty(cookval)) {
			return null;
		}
		
		String val = StringUtils.isEmpty(parval)?cookval:parval;
		return StockService.getCook(val);
	}


	private String getCookie(HttpServletRequest request, String cookName) {
		// 设置后台访问的用户的信息
		Cookie [] cookies = request.getCookies();
		String cookVal = "";
		for (Cookie cookie : cookies) {
			if (cookName.equals(cookie.getName())) {
				cookVal = cookie.getValue();
				break;
			}
		}
		return cookVal;
	}
}
