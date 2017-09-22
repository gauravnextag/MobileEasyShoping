package com.mob.shopping.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OTPOperationDTO {

	private String otpStatus;

	private String additionalData;

	private String authToken;

	private String userId;

	public OTPOperationDTO(final String otpStatus) {
		this.otpStatus = otpStatus;
	}

	public OTPOperationDTO(final String otpStatus, final String additionalData) {
		this.otpStatus = otpStatus;
		this.additionalData = additionalData;
	}

	public String getOtpStatus() {
		return otpStatus;
	}

	public void setOtpStatus(final String otpStatus) {
		this.otpStatus = otpStatus;
	}

	public String getAdditionalData() {
		return additionalData;
	}

	public void setAdditionalData(final String additionalData) {
		this.additionalData = additionalData;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("otpStatus", otpStatus)
                .append("additionalData", additionalData)
                .append("authToken", authToken)
                .append("userId", userId)
                .toString();
    }

}
