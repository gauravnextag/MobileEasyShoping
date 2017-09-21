package com.mob.shopping.adapter;


import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.exception.MessageBrokerException;

public interface MessageBrokerAdapter {

	public boolean sendMessage(String msisdn, String shortCode, String smsText) throws MessageBrokerException, BusinessException;
}
