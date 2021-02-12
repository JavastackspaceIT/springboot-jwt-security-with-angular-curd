package com.javastackspaceit.dto;

import java.util.List;

public class ApiResponse {
	private int status;
	private String message;
	private List<UserResponse> result;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<UserResponse> getResult() {
		return result;
	}

	public void setResult(List<UserResponse> result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "ApiResponse [status=" + status + ", message=" + message + ", result=" + result + "]";
	}

}
