package com.mob.shopping.beans.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BankResponse implements Serializable {

    private static final long serialVersionUID = -3774284009364971257L;
    private String feSessionId;
    private String errorMessage;
    private String errorCode;
    private String leinStatus; //0 success or 1 failure;
    private Double balance;
    private String responseCode;

    /**
     * @return the feSessionId
     */
    public String getFeSessionId() {
        return feSessionId;
    }

    /**
     * @param feSessionId the feSessionId to set
     */
    public void setFeSessionId(String feSessionId) {
        this.feSessionId = feSessionId;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @return the balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * @param balance the balance to set
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }

    /**
     * @return the responseCode
     */
    @JsonProperty("@code")
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getLeinStatus() {
        return leinStatus;
    }

    public void setLeinStatus(String leinStatus) {
        this.leinStatus = leinStatus;
    }
}


