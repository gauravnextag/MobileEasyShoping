/*******************************************************************************
 * Copyright (c) 2016, Bharti Airtel Limited. All rights reserved. Bharti Airtel
 * LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/

package com.mob.shopping.exception;


import com.mob.shopping.beans.ErrorBean;

/**
 * The Class BaseApplicationException.
 *
 * @author B0073698
 */
public abstract class BaseApplicationException extends Exception {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The error. */
    private ErrorBean error;

    /**
     * Instantiates a new base application exception.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     * @param t the t
     */
    public BaseApplicationException(String errorCode, String errorMessage, Throwable t) {
        super(errorMessage, t);
        this.error = new ErrorBean(errorCode, errorMessage);
    }

    /**
     * Instantiates a new base application exception.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     */
    public BaseApplicationException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.error = new ErrorBean(errorCode, errorMessage);
    }

    /**
     * Gets the error.
     *
     * @return the error
     */
    public ErrorBean getError() {
        return error;
    }

}
