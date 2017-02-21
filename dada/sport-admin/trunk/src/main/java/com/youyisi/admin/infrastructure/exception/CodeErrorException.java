package com.youyisi.admin.infrastructure.exception;

/**
 * 用户存在异常
 * @author shuye
 * @time 2013-8-12
 */
public class CodeErrorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7510980789927862457L;

	public CodeErrorException(){
		super();
	}
	public CodeErrorException(String message){
		super(message);
	}
}
