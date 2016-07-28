package com.dsb.exception;

public class AccessTokenException extends Exception {

	/**
	 * 无法获取到access_token
	 */
	private static final long serialVersionUID = 1L;

	public AccessTokenException() {
		// TODO Auto-generated constructor stub
		System.err.println("内部错误，无法获取到AccessToken");
	}

	public AccessTokenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public AccessTokenException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public AccessTokenException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
