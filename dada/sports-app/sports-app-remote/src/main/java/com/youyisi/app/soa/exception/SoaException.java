package com.youyisi.app.soa.exception;



public class SoaException extends Exception {

	private static final long serialVersionUID = 9036224037975032316L;
	

	public SoaException(String errorCode) {
		super();
	}

	public SoaException(String errorCode, String message) {
		super(message);
	}

	public SoaException(String errorCode, String message, Throwable cause) {
		super(message, cause);
	}

	public SoaException(String errorCode, Throwable cause) {
		super(cause);
	}

}
