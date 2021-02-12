package com.javastackspaceit.exception;

public class JwtTokenExpireException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JwtTokenExpireException(String exception) {
		super(exception);
	}
}
