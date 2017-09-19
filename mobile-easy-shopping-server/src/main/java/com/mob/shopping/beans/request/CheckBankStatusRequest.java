package com.mob.shopping.beans.request;

import org.apache.commons.lang.builder.ToStringBuilder;

public class CheckBankStatusRequest {

    private String appName;
    private String msisdn;
    private String cafNumber;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getCafNumber() {
        return cafNumber;
    }

    public void setCafNumber(String cafNumber) {
        this.cafNumber = cafNumber;
    }

    @Override
    public String toString() {
        return new org.apache.commons.lang3.builder.ToStringBuilder(this)
                .append("appName", appName)
                .append("msisdn", msisdn)
                .append("cafNumber", cafNumber)
                .toString();
    }
}
