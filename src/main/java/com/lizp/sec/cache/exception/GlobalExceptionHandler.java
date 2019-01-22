package com.lizp.sec.cache.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lizp.sec.cache.util.CodeMsg;
import com.lizp.sec.cache.util.Result;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
	public Result<String> exceptionHandler(HttpServletRequest req, Exception e){
		e.printStackTrace();
		if(e instanceof GlobalException) {
			GlobalException ex = (GlobalException)e;	
			return Result.fail(ex.getCodeMsg());
		}else if(e instanceof BindException) {
			BindException ex = (BindException)e;	
			List<ObjectError> oErrors = ex.getAllErrors();
			String msg = oErrors.get(0).getDefaultMessage();
			return Result.fail(CodeMsg.exception.fillArg(msg));
		}else {
			return Result.fail(CodeMsg.fail);
		}
	}
}
