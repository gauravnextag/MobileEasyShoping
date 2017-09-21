package com.mob.shopping.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.mob.shopping.enums.ResponseCode;

public class RestUtils {

	public static <T> ResponseEntity<RestResponse<T>> successResponse(T data, String message, HttpStatus statusCode) {
		RestResponse<T> response = new RestResponse<T>(Constants.SUCCESS_STATUS, message, data);
		MultiValueMap<String, String> headers = setHeaders(response);
		return new ResponseEntity<RestResponse<T>>(response, headers, statusCode);
	}

	public static <T> ResponseEntity<RestResponse<T>> successResponse(T data) {
		return successResponse(data,"ok" ,HttpStatus.OK);
	}

	public static <T> ResponseEntity<RestResponse<T>> successResponse() {
		return successResponse(null,"ok" ,HttpStatus.OK);
	}
	
	public static <T> ResponseEntity<RestResponse<T>> successResponse(T data, String message) {
		return successResponse(data, message, HttpStatus.OK);
	}

	public static <T> ResponseEntity<RestResponse<?>> errorResponseEntity(String errorMessage,
			HttpStatus statusCode) {
		RestResponse<T> response = new RestResponse<T>(Constants.FAILURE_STATUS, errorMessage, null);
		MultiValueMap<String, String> headers = setHeaders(response);
		return new ResponseEntity<RestResponse<?>>(response, headers, statusCode);
	}

	public static <T> ResponseEntity<RestResponse<?>> errorResponseData(ResponseCode yatraResponseCode,
			HttpStatus statusCode) {
		RestResponse<T> response = new RestResponse<T>(yatraResponseCode.getCode(), yatraResponseCode.getDescription(), null);
		MultiValueMap<String, String> headers = setHeaders(response);
		return new ResponseEntity<RestResponse<?>>(response, headers, statusCode);
	}
	
	public static <T> RestResponse<?> errorResponseData(String errorMessage) {
		return new RestResponse<T>(Constants.FAILURE_STATUS, errorMessage,null);
	}

	public static <T> RestResponse<?> errorResponseEnum(ResponseCode yatraResponseCode) {
		return new RestResponse<T>(yatraResponseCode.getCode(), yatraResponseCode.getDescription(), null);
	}
	
	private static <T> MultiValueMap<String, String> setHeaders(RestResponse<T> response) {
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		String value = null;
		try {
			ObjectMapper mapper =  new ObjectMapper().configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);;
			value = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
		}
		headers.set("code", String.valueOf(value.hashCode()));
		return headers;
	}


}