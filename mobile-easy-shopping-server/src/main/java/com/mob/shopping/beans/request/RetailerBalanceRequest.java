package com.mob.shopping.beans.request;

import org.apache.commons.lang.builder.ToStringBuilder;

public class RetailerBalanceRequest{

    private String lapuNumber;
    private String msisdn;

    /**
     * @return the msisdn
     */
    public String getMsisdn() {
        return msisdn;
    }

    /**
     * @return the lapuNumber
     */
    public String getLapuNumber() {
        return lapuNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("lapuNumber", lapuNumber)
                .append("msisdn", msisdn)
                .toString();
    }
}
