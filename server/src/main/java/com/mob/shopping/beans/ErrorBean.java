/*******************************************************************************
 * Copyright (c) 2016, Bharti Airtel Limited. All rights reserved. Bharti Airtel
 * LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/

package com.mob.shopping.beans;

import java.io.Serializable;

public class ErrorBean implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = -3148954768216648669L;

    /** The error code. */
    private String errorCode;

    /** The error message. */
    private String errorMessage;

    /**
     * Instantiates a new error bean.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     */
    public ErrorBean(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    /**
     * Instantiates a new error bean.
     */
    public ErrorBean() {
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Sets the error code.
     *
     * @param errorCode the new error code
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Sets the error message.
     *
     * @param errorMessage the new error message
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    @Override
    public String toString() {
        return "ErrorBean{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
