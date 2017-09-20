package com.mob.shopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import com.mob.shopping.enums.ResponseCode;


public class BaseApplicationException extends RuntimeException {

	private static final long serialVersionUID = -3774564054778726161L;

	public static final HttpStatus DEFAULT_HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

	public static final String DEFAULT_MESSAGE = "Something Bad Happened!";

	private HttpStatus httpStatus;

	private String message;

	private ResponseCode responseCode;

	public ResponseCode getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(ResponseCode responseCode) {
		message = responseCode.getDescription();
		this.responseCode = responseCode;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BaseApplicationException(HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus == null ? DEFAULT_HTTP_STATUS : httpStatus;
		this.message = StringUtils.isEmpty(message) ? DEFAULT_MESSAGE : message;
	}

	public BaseApplicationException(String message) {
		this(DEFAULT_HTTP_STATUS, message);
	}

	public BaseApplicationException() {
		this(DEFAULT_HTTP_STATUS, DEFAULT_MESSAGE);
	}

	public BaseApplicationException(ResponseCode err) {
		super();
		this.setResponseCode(err);		
	}
}
