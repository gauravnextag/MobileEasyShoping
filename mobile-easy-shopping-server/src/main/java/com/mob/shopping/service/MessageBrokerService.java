package com.mob.shopping.service;

import com.mob.shopping.exception.BaseApplicationException;

public interface MessageBrokerService {

	public boolean sendMessage(String msisdn, String shortCode, String smsText) throws BaseApplicationException;
}
