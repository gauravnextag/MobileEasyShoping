/**
 * 
 */
package com.mob.shopping.controller;

import com.mob.shopping.beans.ErrorBean;
import com.mob.shopping.beans.ResponseBean;
import com.mob.shopping.beans.request.OTPRequestBean;
import com.mob.shopping.entity.ConfigMaster;
import com.mob.shopping.entity.OTP;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.OTPService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Gaurav Aggarwal
 *
 */

@RestController
@RequestMapping(value = "/otp")
public class OTPController {

	@Autowired
	private OTPService otpService;

	@Autowired
	MasterConfigService masterServices;


	private static final Logger LOGGER = LoggerFactory.getLogger(OTPController.class);

	@RequestMapping(value = "/sendOtp", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
	ResponseBean sendOTP(@RequestBody OTPRequestBean otpRequestBean) {
		ResponseBean response = new ResponseBean(null, null);

		try {
			LOGGER.info("OTP REQUEST_INITIATED for user {} ", otpRequestBean.getMsisdn());

			otpService.generateOTP(otpRequestBean.getMsisdn());
		} catch (BusinessException exception) {
			LOGGER.info("BusinessException occurred while generating OTP ", exception);
			response.setError(new ErrorBean(exception.getError().getErrorCode(), exception.getError().getErrorMessage()));
		} catch (Exception e) {
			LOGGER.error("Error stacktrace for send OTP ", e);
			response.setError(new ErrorBean());
		}
		return response;
	}

//	@RequestMapping(value = "/verifyOtp", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//	public @ResponseBody
//    ResponseBean verifyOTP(@RequestBody DSLBean dslBean) {
//
//		ResponseBean response = new ResponseBean(null, null);
//		DSLBean cachedDslBean = null;
//		OTPOperationDTO otpStatus ;
//		DSLBean responseBean;
//		LOGGER.info("Verify OTP REQUEST_INITIATED for user {} ", dslBean.getDslId());
//		try {
//			LOGGER.info("Trying to fetch from cache value {}", dslBean.getDslId());
//			 cachedDslBean = (DSLBean) cacheServices.getMessageFromRedis(dslBean.getDslId());
//
//		} catch (Exception ex) {
//			LOGGER.info("Exception is :: {}", ex);
//			LOGGER.info("Exception occurred while fetching from cache {}. Otp expired", dslBean.getDslId());
//		}
//
//		try {
//			if (null == dslBean.getDslOtp() || dslBean.getDslOtp().getOtpCode().isEmpty() || null== dslBean.getDslOtp().getOtpCode()) {
//				LOGGER.info("No parameters found in verifyOtp request");
//				response.setError(new ErrorBean(CAFConstants.ERROR_CODE_ERROR, CAFConstants.ERROR_MESSAGE));
//				return response;
//			}
//
//			if(cachedDslBean!=null) {
//				LOGGER.info("Found in cache {}",cachedDslBean);
//				otpStatus = dslService.verifyOTPFromCache(cachedDslBean, dslBean);
//			}else{
//				//In case of redis Failure
//				LOGGER.info("Not Found in cache , Trying to verifying from db");
//				dslBean = dslService.getDetailsByDslId(dslBean.getDslId(),dslBean);
//				otpStatus = dslService.verifyOTP(dslBean.getMsisdn(), dslBean.getDslOtp().getOtpCode(),
//					dslBean.getDslOtp().getOtpToken());
//			}
//			if (null == otpStatus) {
//				LOGGER.info("OTP status found null, failed to verify OTP");
//				response.setError(new ErrorBean(CAFConstants.ERROR_CODE_ERROR, CAFConstants.ERROR_MESSAGE));
//				return response;
//			}
//
//			if (otpStatus.getOtpStatus().equalsIgnoreCase(CAFConstants.OTP_VERIFICATION_SUCCESS)) {
//				responseBean = cachedDslBean!=null?cachedDslBean:dslBean;
//				response.setResult(responseBean);
//				response.setError(new ErrorBean("SUCCESS", CAFConstants.OTP_VERIFICATION_SUCCESS));
//
//			} else {
//				response.setError(new ErrorBean(CAFConstants.ERROR_CODE_ERROR, otpStatus.getOtpStatus()));
//			}
//		} catch (Exception e) {
//			LOGGER.error("Exception occurred while verifying OTP and generating token", e);
//			response.setError(new ErrorBean(CAFConstants.ERROR_CODE_ERROR, CAFConstants.ERROR_MESSAGE));
//		}
//		return response;
//	}

//	/**
//	 * Method to create OAuth2AccessToken for user
//	 *
//	 * @param otpBean
//	 * @param request
//	 * @return
//	 */
//	private OAuth2AccessToken createOAuthToken(OTPBean otpBean, HashMap<String, String> request) {
//
//		String grantType = "client_credentials";
//		Boolean isForced = false;
//
//		if (otpBean.getParameters().containsKey("isForced")) {
//			isForced = Boolean.valueOf(otpBean.getParameters().get("isForced"));
//		}
//
//		DefaultAuthorizationRequest authorizationRequest = new DefaultAuthorizationRequest(
//				getAuthorizationRequestManager().createAuthorizationRequest(request));
//		if (isAuthCodeRequest(otpBean.getParameters()) || isRefreshTokenRequest(otpBean.getParameters())) {
//			// The scope was requested or determined during the authorization
//			// step
//			if (!authorizationRequest.getScope().isEmpty()) {
//				LOGGER.debug("Clearing scope of incoming auth code request");
//				authorizationRequest.setScope(Collections.<String> emptySet());
//			}
//		}
//		if (isRefreshTokenRequest(otpBean.getParameters())) {
//			// A refresh token has its own default scopes, so we should ignore
//			// any added by the factory here.
//			authorizationRequest.setScope(OAuth2Utils.parseParameterList(otpBean.getParameters().get("scope")));
//		}
//		OAuth2AccessToken token = getTokenGranter().grant(grantType, authorizationRequest);
//		LOGGER.debug("Token generated : {}", token);
//
//		if (token == null) {
//			LOGGER.info("Token is null");
//			throw new UnsupportedGrantTypeException("Unsupported grant type: " + grantType);
//		}
//
//		if (isForced != null && isForced) {
//			if (isAlreadyLoggedIn(token)) {
//				// call logout here
//				LOGGER.info("Logging out the already logged In user.");
//				tokenServices.revokeToken(token.getValue());
//				// once logout successful generate new token
//				LOGGER.info("Logging in the user on new device.");
//				token = getTokenGranter().grant(grantType, authorizationRequest);
//			}
//		}
//		return token;
//	}
//
//	/**
//	 * Method to create JWT Token for user
//	 *
//	 * @param agentBean
//	 * @param loginResponse
//	 * @return
//	 * @throws ControllerException
//	 */
//	private String createJWTToken(AgentBean agentBean, JWTLoginResponseBean loginResponse) throws ControllerException {
//
//		String jwtToken = null;
//
//		Date now = new Date();
//		long validTill = now.getTime() + jwtTokenValidity;
//		loginResponse.setCode(CAFConstants.SUCCESS_CODE);
//		loginResponse.setValidTill(validTill);
//		loginResponse.setLapuId(agentBean.getMsisdn());
//
//		JWTClaimsSet claimsSet = new JWTClaimsSet();
//		claimsSet.setSubjectClaim(agentBean.getMsisdn());
//		claimsSet.setIssuedAtClaim(now.getTime());
//		claimsSet.setIssuerClaim(CAFConstants.JWT_TOKEN_ISSUER);
//		claimsSet.setExpirationTimeClaim(validTill);
//		claimsSet.setNotBeforeClaim(now.getTime());
//		claimsSet.setCustomClaim("postpaidSfoCode", agentBean.getPostpaidSfoCode());
//		claimsSet.setCustomClaim("dslSfoCode", agentBean.getDslSfoCode());
//		claimsSet.setCustomClaim("dslChannelId", agentBean.getDslChannelId());
//		claimsSet.setCustomClaim("circleId", String.valueOf(((CircleBean) agentBean.getCircle()).getCircleId()));
//		claimsSet.setCustomClaim("circleIdEai", String.valueOf(((CircleBean) agentBean.getCircle()).getCircleIdEAI()));
//		claimsSet.setCustomClaim("userId", agentBean.getUserId());
//		claimsSet.setCustomClaim("lob", agentBean.getLob());
//
//		if (StringUtils.isNotEmpty(agentBean.getLob()) && agentBean.getLob().equalsIgnoreCase("Telemedia")) {
//			claimsSet.setCustomClaim("roles", "ROLE_DSL_AGENT");
//		} else {
//			claimsSet.setCustomClaim("roles", "ROLE_RETAILER");
//		}
//
//		try {
//			jwtToken = this.signAndSerializeJWT(claimsSet, CAFConstants.JWT_SECURITY_KEY);
//		} catch(ControllerException exception) {
//			throw exception;
//		}
//
//		return jwtToken;
//
//	}
//
//	/**
//	 * Sign and serialize JWT.
//	 *
//	 * @param claimsSet
//	 *            the claims set
//	 * @param secret
//	 *            the secret
//	 * @return the string
//	 * @throws ControllerException
//	 *             the controller exception
//	 */
//	private String signAndSerializeJWT(ReadOnlyJWTClaimsSet claimsSet, String secret) throws ControllerException {
//		JWSSigner signer = new MACSigner(secret.getBytes());
//		SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);
//		try {
//			LOGGER.info("Request INSIDE signAndSerializeJWT");
//			signedJWT.sign(signer);
//			return signedJWT.serialize();
//		} catch (JOSEException e) {
//			LOGGER.info("Request INSIDE signAndSerializeJWT :: {}", ExceptionUtils.getStackTrace(e));
//			LOGGER.error("Error stacktrace", e);
//			throw new ControllerException(CAFConstants.ERROR_CODE_ERROR, CAFConstants.ERROR_MESSAGE, e);
//		}
//	}
//
//	/**
//	 * Checks if is refresh token request.
//	 *
//	 * @param parameters
//	 *            the parameters
//	 * @return true, if is refresh token request
//	 */
//	private boolean isRefreshTokenRequest(Map<String, String> parameters) {
//		return "refresh_token".equals(parameters.get("grant_type")) && parameters.get("refresh_token") != null;
//	}
//
//	/**
//	 * Checks if is auth code request.
//	 *
//	 * @param parameters
//	 *            the parameters
//	 * @return true, if is auth code request
//	 */
//	private boolean isAuthCodeRequest(Map<String, String> parameters) {
//		return "authorization_code".equals(parameters.get("grant_type")) && parameters.get("code") != null;
//	}
//
//	/**
//	 * Checks if is already logged in.
//	 *
//	 * @param token
//	 *            the token
//	 * @return true, if is already logged in
//	 */
//	private boolean isAlreadyLoggedIn(OAuth2AccessToken token) {
//		LOGGER.info("value of token expires is : {}", token.getExpiresIn());
//		LOGGER.info("value of token validity is : {} ", tokenValidity);
//		if (tokenValidity - token.getExpiresIn() >= 3) {
//			LOGGER.info("returning true!!");
//			return true;
//		}
//		LOGGER.info("returning false!!");
//		return false;
//	}
//
//	/**
//	 * Sets the token granter.
//	 *
//	 * @param tokenGranter
//	 *            the new token granter
//	 */
//	public void setTokenGranter(TokenGranter tokenGranter) {
//		this.tokenGranter = tokenGranter;
//	}
//
//	/**
//	 * Gets the token granter.
//	 *
//	 * @return the token granter
//	 */
//	protected TokenGranter getTokenGranter() {
//		return tokenGranter;
//	}
//
//	/**
//	 * Gets the client details service.
//	 *
//	 * @return the client details service
//	 */
//	protected ClientDetailsService getClientDetailsService() {
//		return clientDetailsService;
//	}
//
//	/**
//	 * Sets the client details service.
//	 *
//	 * @param clientDetailsService
//	 *            the new client details service
//	 */
//	public void setClientDetailsService(ClientDetailsService clientDetailsService) {
//		this.clientDetailsService = clientDetailsService;
//	}
//
//	/**
//	 * Gets the authorization request manager.
//	 *
//	 * @return the authorization request manager
//	 */
//	protected AuthorizationRequestManager getAuthorizationRequestManager() {
//		return authorizationRequestManager;
//	}
//
//	/**
//	 * Sets the authorization request manager.
//	 *
//	 * @param authorizationRequestManager
//	 *            the new authorization request manager
//	 */
//	public void setAuthorizationRequestManager(AuthorizationRequestManager authorizationRequestManager) {
//		this.authorizationRequestManager = authorizationRequestManager;
//	}


}
