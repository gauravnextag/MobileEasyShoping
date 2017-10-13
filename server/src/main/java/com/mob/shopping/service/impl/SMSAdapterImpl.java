/**
 * @Ajay Mishra
 */
package com.mob.shopping.service.impl;

import java.net.URI;
import java.net.URLEncoder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.mob.shopping.beans.request.TokenResponse;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.SMSAdapterService;
import com.mob.shopping.util.Constants;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

@Service
public class SMSAdapterImpl implements SMSAdapterService {
	@Autowired
	MasterConfigService masterConfigService;

	private static final Logger logger = LoggerFactory.getLogger(DistributorServicesImpl.class);

	private URI getAuthBaseURI() {
		return UriBuilder.fromUri(masterConfigService.getValueByKey(Constants.AIS.SMS.BASE_ENDPOINT)).build();
	}

	@Override
	public boolean sendMessage(String msisdn, String message) {
		String methodName = "sendSms >>" + "msisdn::" + msisdn + ", message::" + message + " : ";
		logger.info(methodName + "starts");

		boolean isSmsSend = false;
		java.net.HttpURLConnection.setFollowRedirects(false);
		try {
			String SMSC_ENDPOINT = null;

			PostMethod postMethod = null;
			SMSC_ENDPOINT = masterConfigService.getValueByKey(Constants.AIS.SMS.SMSC_ENDPOINT);
			logger.info(methodName + "Endpoint [" + SMSC_ENDPOINT + "]");

			postMethod = new PostMethod(SMSC_ENDPOINT);
			postMethod.addParameter("msisdn", Constants.MOBILE_PREFIX_INDIA + msisdn);
			postMethod.addParameter("text", URLEncoder.encode(message, Constants.REQUEST_ENCODING_UTF8));
			String tempToken = getAuthenticationToken();

			postMethod.addRequestHeader(Constants.AUTHORIZATION, Constants.BEARER + tempToken);
			HttpConnectionManagerParams hmcp = new HttpConnectionManagerParams();
			hmcp.setConnectionTimeout(20000);
			hmcp.setSoTimeout(5000);

			SimpleHttpConnectionManager scm = new SimpleHttpConnectionManager();
			scm.setParams(hmcp);

			HttpClient httpclient = new HttpClient(scm);
			int responseCode = httpclient.executeMethod(postMethod);

			if (responseCode == Constants.HTTP_STATUS.OK)
				isSmsSend = true;

			String responseBody = postMethod.getResponseBodyAsString();
			logger.info(methodName + "isSmsSend::" + isSmsSend + ", responseCode::" + responseCode + ", responseBody::"
					+ responseBody);
		} catch (Exception e) {
			logger.error(methodName + "message could not be send by MB", e);
		}
		logger.info(methodName + "ends.");

		return isSmsSend;
	}

	private String getAuthenticationToken() {
		String methodName = "getAuthenticationToken >> ";
		logger.info(methodName + "starts");

		try {
			ClientConfig config = new DefaultClientConfig();
			Client client = Client.create(config);
			WebResource service = client.resource(getAuthBaseURI());

			Form form = new Form();
			form.add(Constants.CLIENT_ID, masterConfigService.getValueByKey(Constants.AIS.SMS.USER));
			form.add(Constants.CLIENT_SECRET, masterConfigService.getValueByKey(Constants.AIS.SMS.PASSWORD));
			form.add(Constants.GRANT_TYPE, masterConfigService.getValueByKey(Constants.AIS.GRANT_TYPE));
			ClientResponse response = service.path(Constants.AIS.TOKEN).type(MediaType.APPLICATION_FORM_URLENCODED)
					.post(ClientResponse.class, form);
			String responseString = response.getEntity(String.class);
			logger.info(methodName + "responseString::" + responseString);

			Gson gson = new Gson();
			final TokenResponse tokenResponse = gson.fromJson(responseString, TokenResponse.class);
			logger.info("Token response " + tokenResponse.getAccess_token());
			return tokenResponse.getAccess_token();
		} catch (Exception exception) {
			logger.error(methodName + "error occured", exception);
			return null;
		} finally {
			logger.info(methodName + "ends.");
		}
	}

}
