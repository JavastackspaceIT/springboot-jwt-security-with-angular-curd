package com.javastackspaceit.exception;

public class UserRecordsNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserRecordsNotFoundException(String exception) {
		super(exception);
	}
}
