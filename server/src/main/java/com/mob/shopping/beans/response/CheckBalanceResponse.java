package com.mob.shopping.beans.response;

import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;

/**
 * Created by B0097545 on 9/11/17.
 */

public class CheckBalanceResponse implements Serializable {
    private static final long serialVersionUID = -4379360849220562505L;
    private Double retailerBalance;
    private String status;
    private String message;

    public Double getRetailerBalance() {
        return retailerBalance;
    }

    public void setRetailerBalance(Double retailerBalance) {
        this.retailerBalance = retailerBalance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("retailerBalance", retailerBalance)
                .append("status", status)
                .append("message", message)
                .toString();
    }
}
