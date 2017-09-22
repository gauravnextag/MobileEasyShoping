package com.mob.shopping.beans.request;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class OTPRequestBean implements Serializable {

   private String msisdn;
   private String otp;
   private int userType;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("msisdn", msisdn)
                .append("otp", otp)
                .append("userType", userType)
                .toString();
    }
}
