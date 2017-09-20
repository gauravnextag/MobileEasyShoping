/*******************************************************************************
 * Copyright (c) 2016, Bharti Airtel Limited. All rights reserved. Bharti Airtel
 * LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/

package com.mob.shopping.exception;

import com.mob.shopping.enums.ResponseCode;

/**
 * The Class BusinessException.
 *
 * @author B0073698
 */
public class BusinessException extends BaseApplicationException {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new business exception.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     * @param t the t
     */

    public BusinessException() {
    	super();
    }
    public BusinessException(ResponseCode responseCode) {
        super(responseCode);
    }

}
