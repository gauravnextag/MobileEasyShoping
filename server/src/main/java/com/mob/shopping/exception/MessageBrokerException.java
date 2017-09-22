package com.mob.shopping.exception;

import com.mob.shopping.constants.enums.ResponseCode;

public class MessageBrokerException extends BaseApplicationException {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;


    public MessageBrokerException(ResponseCode responseCode) {
        super(responseCode);

    }
    public MessageBrokerException() {
        super();

    }

}
