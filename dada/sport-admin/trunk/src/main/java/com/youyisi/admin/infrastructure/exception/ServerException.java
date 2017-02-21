package com.youyisi.admin.infrastructure.exception;

import org.springframework.core.ErrorCoded;


public class ServerException extends Exception implements ErrorCoded {

	private static final long serialVersionUID = 9036224037975032316L;
	
	private String errorCode;

	public ServerException(String errorCode) {
		super();
		setErrorCode(errorCode);
	}

	public ServerException(String errorCode, String message) {
		super(message);
		setErrorCode(errorCode);
	}

	public ServerException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		setErrorCode(errorCode);
	}

	public ServerException(String errorCode, Throwable cause) {
		super(cause);
		setErrorCode(errorCode);
	}
	
	private void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
