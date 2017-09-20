package com.mob.shopping.exception;

public class MessageBrokerException extends BaseApplicationException{

	 /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new MessageBrokerException.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     * @param t the t
     */
    public MessageBrokerException(String errorCode, String errorMessage, Throwable t) {
        super(errorCode, errorMessage, t);
    }

    /**
     * Instantiates a new MessageBrokerException.
     *
     * @param errorCode the error code
     * @param errorMessage the error message
     */
    public MessageBrokerException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
