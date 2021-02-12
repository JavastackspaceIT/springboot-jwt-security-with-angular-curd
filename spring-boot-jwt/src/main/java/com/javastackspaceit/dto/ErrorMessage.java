package com.javastackspaceit.dto;

public class ErrorMessage {

	private int code;
	private String message;

	public ErrorMessage() {
		// TODO Auto-generated constructor stub
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorMessage [code=" + code + ", message=" + message + "]";
	}

}
