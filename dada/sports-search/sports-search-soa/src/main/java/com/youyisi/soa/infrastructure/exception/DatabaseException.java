package com.youyisi.soa.infrastructure.exception;

/**
 * 数据库异常
 * 
 * @author shuye
 * @time 上午10:17:49
 */
public class DatabaseException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(Throwable cause) {
		super(cause);
	}

}
