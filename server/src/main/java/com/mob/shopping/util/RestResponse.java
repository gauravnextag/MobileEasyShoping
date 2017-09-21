package com.mob.shopping.util;

public class RestResponse<T> {

	private String message;

	private int status;

	private T data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RestResponse() {}

	public RestResponse(T data) {
		this(Constants.SUCCESS_STATUS, null, data);
	}

	public RestResponse(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	@Override
	public String toString() {
		return "RestResponse [message=" + message + ", status=" + status + ", data=" + data + "]";
	}

}
