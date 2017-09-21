/*******************************************************************************
 * Copyright (c) 2016, Bharti Airtel Limited. All rights reserved. Bharti Airtel
 * LTD. PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/

package com.mob.shopping.beans;

import java.io.Serializable;

public class ResponseBean implements Serializable {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 5139829430950669640L;

    /**
     * The error.
     */
    private ErrorBean error;
    private StatusBean status;

    /**
     * The result.
     */
    private Object result;

    /**
     * Instantiates a new response bean.
     *
     * @param error  the error
     * @param result the result
     */
    public ResponseBean(ErrorBean error, Object result) {
        this.error = error;
        this.result = result;
    }

    /**
     * Instantiates a new response bean.
     */
    public ResponseBean() {
    }

    /**
     * Gets the error.
     *
     * @return the error
     */
    public ErrorBean getError() {
        return error;
    }

    /**
     * Sets the error.
     *
     * @param error the new error
     */
    public void setError(ErrorBean error) {
        this.error = error;
    }

    /**
     * Gets the result.
     *
     * @return the result
     */
    public Object getResult() {
        return result;
    }

    /**
     * Sets the result.
     *
     * @param result the new result
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */

    public StatusBean getStatus() {
        return status;
    }

    public void setStatus(StatusBean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResponseBean [error=" + error + ", result=" + result + "]";
    }
}
