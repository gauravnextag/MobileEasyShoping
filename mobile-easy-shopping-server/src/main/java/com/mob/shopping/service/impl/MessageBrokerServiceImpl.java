package com.mob.shopping.service.impl;

import com.mob.shopping.adapter.MessageBrokerAdapter;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.service.MessageBrokerService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageBrokerServiceImpl implements MessageBrokerService {

	@Autowired
	private MessageBrokerAdapter messageBrokerAdapter;


	/**
	 * The Constant LOGGER.
	 */
	private final Logger LOGGER = LoggerFactory.getLogger(MessageBrokerServiceImpl.class);

	public boolean sendMessage(String msisdn, String shortCode, String smsText) throws Exception {
		if (!StringUtils.isEmpty(msisdn) && !StringUtils.isEmpty(shortCode) && !StringUtils.isEmpty(smsText)) {
			LOGGER.info("Inside MessageBrokerServiceImpl.sendMessage : sending message to : {} from shortcode {} sms text {} ",
					new Object[] { msisdn, shortCode, smsText });
			
			return messageBrokerAdapter.sendMessage(msisdn, shortCode, smsText);
		}
		throw new BusinessException(ErrorConstants.ERROR_CODE,
				ErrorConstants.ERROR_MESSAGE_MESSAGE_DETAILS_MANDATORY);
	}



}
