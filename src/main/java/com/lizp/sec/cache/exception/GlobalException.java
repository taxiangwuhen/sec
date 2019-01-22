package com.lizp.sec.cache.exception;

import com.lizp.sec.cache.util.CodeMsg;

public class GlobalException extends RuntimeException{
    private CodeMsg codeMsg;
	
	public GlobalException(CodeMsg codeMsg) {
		super(codeMsg.toString());
		this.codeMsg = codeMsg;
	}

	public CodeMsg getCodeMsg() {
		return codeMsg;
	}

}
