/**
 * 
 */
package com.mob.shopping.controller;

import com.mob.shopping.beans.OTPOperationDTO;
import com.mob.shopping.beans.request.OTPRequestBean;
import com.mob.shopping.entity.OTP;
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

	@RequestMapping(value = "login/sendOtp", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public 
	ResponseEntity<RestResponse<OTP>> loginSendOTP(@RequestBody OTPRequestBean otpRequestBean) throws BaseApplicationException {
        String method = "[CONTROLLER] sendOTP>>>> ::::";
        logger.info(method);
        return RestUtils.successResponse(otpService.generateOTP(otpRequestBean.getMsisdn(),Boolean.TRUE));
	}

	@RequestMapping(value = "login/verifyOtp", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public 
    ResponseEntity<RestResponse<OTPOperationDTO>> loginVerifyOTP(@RequestBody OTPRequestBean otpRequestBean) {
        String method = "[CONTROLLER] verifyOTP>>>> ::::";
        logger.info(method);
		return RestUtils.successResponse(otpService.verifyOTP(otpRequestBean.getMsisdn(), otpRequestBean.getOtp(),
				otpRequestBean.getUserId()));
	}
	
	
	@RequestMapping(value = "/sendOtp", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public 
	ResponseEntity<RestResponse<OTP>> sendOTP(@RequestBody OTPRequestBean otpRequestBean) throws BaseApplicationException {
        String method = "[CONTROLLER] sendOTP>>>> ::::";
        logger.info(method);
        return RestUtils.successResponse(otpService.generateOTP(otpRequestBean.getMsisdn(),Boolean.FALSE));
	}

	@RequestMapping(value = "/verifyOtp", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public 
    ResponseEntity<RestResponse<OTPOperationDTO>> verifyOTP(@RequestBody OTPRequestBean otpRequestBean) {
        String method = "[CONTROLLER] verifyOTP>>>> ::::";
        logger.info(method);
		return RestUtils.successResponse(otpService.verifyOTP(otpRequestBean.getMsisdn(), otpRequestBean.getOtp(),
				otpRequestBean.getUserId()));
	}



}
