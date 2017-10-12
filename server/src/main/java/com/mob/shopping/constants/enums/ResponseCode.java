package com.mob.shopping.constants.enums;

import com.mob.shopping.util.CommonUtility;

public enum ResponseCode {
	
	APP_SUCCESS(0, "success"),
	SUCCESS(200, "SUCCESS"),
	INTERNAL_SERVER_ERROR(5000, "Your request could not be served by the system. Please try again!"),

	GENRAL_ERROR(515, "Your request could not served. Please try again later"),
	INVALID_TOKEN(5001, "Your request could not served. Invalid Token provided!"),
	TOKEN_EXPIRED(5002, "Your request could not served. Token expired!"),
	ERROR_MESSAGE__DETAILS_MANDATORY(5003, "Please provide message details"),
	ERROR_MESSAGE_DAO(5004, "Error creating record. Please check the data and retry"),
	INVALID_PARAMETER(5005, "Your request could not served. Invalid parameter!"),
	ERROR_MESSAGE_MESSAGE_SEND_FAILED(5006,"Error while sending message. Please check the data and retry"),
	RETAILOR_NOT_FOUND(5007,"No retailor found"),
    MAX_OTP_ATTEMPT_REACHED(5008,"Max otp Attempt reached"),
	INVALID_USER(5009,"Invalid user. Please register"),
	USER_NOT_AUTHORIZE(5010,"User. not authorize. Please register"),
	ALREADY_REGISTER(5011," User is already register"),
	RETAILER_REQUEST_PENDING(5012," Your request is pending . Please contact your Distributor to approve your account"),
	NO_PENDING_USER(5013," No Pending user found"),
	DISTRIBUTOR_NOT_FOUND(5014,"No distributor found"),
	INVALID_OTP(5015, "Your request could not served. Invalid Otp!"),
	CUSTOMER_ORDER_ALREADY_PLACED(5012," Customer has already placed an order");

	private final int code;
	private final String description;

	private ResponseCode(final int code, final String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ResponseCode getCode(final int code) {
		for (final ResponseCode responseCode : ResponseCode.values()) {
			if (responseCode.getCode() == code) {
				return responseCode;
			}
		}
		return INTERNAL_SERVER_ERROR;
	}

	public static ResponseCode getResponseCodeByMessage(final String message) {
		for (final ResponseCode responseCode : ResponseCode.values()) {
			if (message.equals(responseCode.getDescription())) {
				return responseCode;
			}
		}
		return INTERNAL_SERVER_ERROR;
	}

	public String getDescriptionWithAdditionalInfo(final String additionalInfo) {
		String msg = getDescription();
		msg = ((msg != null && !CommonUtility.isValidString(additionalInfo)) ? msg.trim() : additionalInfo);
		return msg;
	}

}
