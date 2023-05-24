package com.notes.saver.core.dto;

public class ApiResponse<T> {
	private Boolean status;
	private String message;
	private T data;

	private ApiResponse() {
	}

	public ApiResponse(Boolean status, T data,String message) {
		super();
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
