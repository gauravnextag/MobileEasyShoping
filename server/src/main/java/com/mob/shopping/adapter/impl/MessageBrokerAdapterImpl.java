package com.mob.shopping.adapter.impl;

import com.mob.shopping.adapter.MessageBrokerAdapter;
import com.mob.shopping.constants.ConfigConstants;
import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.exception.MessageBrokerException;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.util.CommonUtils;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageBrokerAdapterImpl implements MessageBrokerAdapter {

	/** The producer template. */

	@Autowired
	MasterConfigService masterConfigService;
	@Autowired
    ProducerTemplate producerTemplate;


	@Autowired
	RestTemplate restTemplate;

	/** The logger. */
	private final Logger LOGGER = LoggerFactory.getLogger(MessageBrokerAdapterImpl.class);

	public boolean sendMessage(String msisdn, String shortCode, String smsText) throws MessageBrokerException, BusinessException {
		//String requestXML = MessageFormat.format(messageFormat, new Object[]{msisdn, shortCode, smsText});
		Map<String, Object> mapRequest = new HashMap<String, Object>();
		mapRequest.put("msisdn", msisdn);
		mapRequest.put("shortcode", shortCode);
		mapRequest.put("smsText", smsText);
		String requestXML = producerTemplate.requestBodyAndHeaders("direct:performFreemarkerTemplatingForMessageBroker",
				null, mapRequest, String.class);
		
		LOGGER.info("Inside MessageBrokerAdapterImpl.sendMessage request {}", requestXML);
		HttpHeaders headers = CommonUtils.getHttpEntityHeaders(masterConfigService.getValueByKey(ConfigConstants.MESSAGE_BROKER_USER_NAME), masterConfigService.getValueByKey(ConfigConstants.MESSAGE_BROKER_PASSWORD),
				MediaType.TEXT_XML);
		HttpEntity<String> entity = new HttpEntity<String>(requestXML, headers);

		String responseString = restTemplate.postForObject(masterConfigService.getValueByKey(ConfigConstants.MESSAGE_BROKER_URL), entity, String.class);
		LOGGER.info("Inside MessageBrokerAdapterImpl.sendMessage response {} to msisdn {}", responseString, msisdn);
		if (responseString.equalsIgnoreCase("ok")) {
			return true;
		}
		LOGGER.info("Inside MessageBrokerAdapterImpl.sendMessage error while sending message {} to msisdn {}",
				responseString, msisdn);
		throw new MessageBrokerException(ResponseCode.ERROR_MESSAGE_MESSAGE_SEND_FAILED);
	}

}
