package com.mob.shopping.service;

public interface MessageBrokerService {

	public boolean sendMessage(String msisdn, String shortCode, String smsText) throws Exception;
}
