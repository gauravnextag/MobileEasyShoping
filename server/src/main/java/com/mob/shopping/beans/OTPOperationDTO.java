package com.mob.shopping.beans;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class OTPOperationDTO {

	private String otpStatus;

	private String additionalData;

	private String authToken;

	private Long userId;

	private Integer userType;

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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
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

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
}
