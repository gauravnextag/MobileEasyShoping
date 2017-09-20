package com.mob.shopping.beans.request;

import java.io.Serializable;

public class OTPRequestBean implements Serializable {

   private String msisdn;

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

}
