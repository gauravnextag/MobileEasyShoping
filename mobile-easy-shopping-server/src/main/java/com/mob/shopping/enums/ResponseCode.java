package com.mob.shopping.enums;

import com.mob.shopping.util.CommonUtility;

public enum ResponseCode {
	
	APP_SUCCESS(0, "success"),
	SUCCESS(200, "SUCCESS"),
	INTERNAL_SERVER_ERROR(5000, "Your request could not be served by the system. Please try again!"),
	GENRAL_ERROR(5001, "Your request could not served. Please try again later"),
	INVALID_TOKEN(5001, "Your request could not served. Invalid Token provided!"),
	TOKEN_EXPIRED(5001, "Your request could not served. Token expired!");
	


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
