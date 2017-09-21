package com.mob.shopping.beans;

public class OTPOperationDTO {

	private String otpStatus;

	private String additionalData;

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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OTPOperationDTO [otpStatus=" + otpStatus + ", additionalData=" + additionalData + "]";
	}
	
}
