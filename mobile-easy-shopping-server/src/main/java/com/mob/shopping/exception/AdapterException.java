/*******************************************************************************
 * Copyright (c) 2016, Bharti Airtel Limited. All rights reserved. Bharti Airtel
 * LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/

package com.mob.shopping.exception;

/**
 * The Class AdapterException.
 *
 * @author B0073698
 */
public class AdapterException extends BaseApplicationException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new adapter exception.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     * @param t the t
     */
    public AdapterException(String errorCode, String errorMessage, Throwable t) {
        super(errorCode, errorMessage, t);
    }

    public AdapterException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
