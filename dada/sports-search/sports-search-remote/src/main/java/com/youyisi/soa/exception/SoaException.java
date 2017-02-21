package com.youyisi.soa.exception;

import org.springframework.core.ErrorCoded;


public class SoaException extends Exception implements ErrorCoded {

	private static final long serialVersionUID = 9036224037975032316L;
	
	private String errorCode;

	public SoaException(String errorCode) {
		super();
		setErrorCode(errorCode);
	}

	public SoaException(String errorCode, String message) {
		super(message);
		setErrorCode(errorCode);
	}

	public SoaException(String errorCode, String message, Throwable cause) {
		super(message, cause);
		setErrorCode(errorCode);
	}

	public SoaException(String errorCode, Throwable cause) {
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
