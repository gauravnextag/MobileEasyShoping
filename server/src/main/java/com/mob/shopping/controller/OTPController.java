/**
 * 
 */
package com.mob.shopping.controller;

import com.mob.shopping.beans.OTPOperationDTO;
import com.mob.shopping.beans.request.OTPRequestBean;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.OTPService;
import com.mob.shopping.util.RestResponse;
import com.mob.shopping.util.RestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


	private static final Logger logger = LoggerFactory.getLogger(OTPController.class);

	@RequestMapping(value = "/sendOtp", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<RestResponse<Boolean>> sendOTP(@RequestBody OTPRequestBean otpRequestBean) throws BaseApplicationException {
        String method = "[CONTROLLER] sendOTP>>>> ::::";
        logger.info(method);
        otpService.generateOTP(otpRequestBean.getMsisdn(),otpRequestBean.getUserType());
        return RestUtils.successResponse(Boolean.TRUE);
	}

	@RequestMapping(value = "/verifyOtp", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody
    ResponseEntity<RestResponse<OTPOperationDTO>> verifyOTP(@RequestBody OTPRequestBean otpRequestBean) {
        String method = "[CONTROLLER] verifyOTP>>>> ::::";
        logger.info(method);
		return RestUtils.successResponse(otpService.verifyOTP(otpRequestBean.getMsisdn(), otpRequestBean.getOtp(),otpRequestBean.getUserType()));
	}

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
