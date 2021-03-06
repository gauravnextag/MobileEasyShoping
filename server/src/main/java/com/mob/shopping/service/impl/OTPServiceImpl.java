package com.mob.shopping.service.impl;

import java.text.MessageFormat;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mob.shopping.beans.OTPOperationDTO;
import com.mob.shopping.constants.ConfigConstants;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.entity.OTP;
import com.mob.shopping.entity.User;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.repository.CustomerDao;
import com.mob.shopping.repository.DistributorDao;
import com.mob.shopping.repository.OTPDao;
import com.mob.shopping.repository.RetailerDao;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.OTPService;
import com.mob.shopping.service.SMSAdapterService;
import com.mob.shopping.service.UserService;
import com.mob.shopping.util.AuthUtils;

@Component("OTPServiceImpl")
public class OTPServiceImpl implements OTPService {

	private final Logger logger = LoggerFactory.getLogger(OTPServiceImpl.class);

	@Autowired
	private OTPDao otpDao;

	@Autowired
	MasterConfigService masterConfigService;

	@Autowired
	CustomerDao customerDao;
	@Autowired
	RetailerDao retailerDao;
	@Autowired
	DistributorDao distributorDao;

	@Autowired
	UserService userService;

	@Autowired
	private SMSAdapterService messageBrokerService;

	public OTPOperationDTO verifyOTP(String msisdn, String otp, Long userId) {

		OTPOperationDTO otpOperationDTO = null;
		String key = "";
		// check user valid
		User user = userService.findByUserIdAndMSISDN(userId, msisdn);
		otpOperationDTO = otpDao.verifyOTP(msisdn, otp);
		if (otpOperationDTO.getOtpStatus().equals(ErrorConstants.OTP_VERIFICATION_SUCCESS)) {
			key = key.concat(msisdn).concat(":").concat(user.getUserType().toString()).concat(":")
					.concat(userId.toString());
			otpOperationDTO.setAuthToken(AuthUtils.createToken(key));
		}
		otpOperationDTO.setUserId(user.getUserId());
		otpOperationDTO.setUserType(user.getUserType());
		return otpOperationDTO;
	}

	public boolean isMaxOTPAttempt(OTP otp) {
		if (otp.getAttempts().intValue() >= Integer
				.parseInt(masterConfigService.getValueByKey(ConfigConstants.MAX_OTP_ATTEMPT))) {
			logger.info("areOTPGenerationExhausted is TRUE, maxOTPAttempts::"
					+ masterConfigService.getValueByKey(ConfigConstants.MAX_OTP_ATTEMPT));
			return true;
		}

		return false;
	}

	public OTP generateOTP(String msisdn, Boolean isRegisteredUser) throws BaseApplicationException {

		Long userId = null;

		try {
			User user = userService.findByMSISDN(msisdn);
			logger.info("user:{}", user.toString());
			userId = user.getUserId();
		} catch (BaseApplicationException e) {
			if (isRegisteredUser) {
				throw new BaseApplicationException(ResponseCode.INVALID_USER);
			}
		}

		OTP otp = null;
		Long oldAttempts = Long.valueOf(0);
		Long id = null;
		UUID uuid = UUID.randomUUID();
		String otpString = Long.toString(Math.abs(uuid.getLeastSignificantBits() % 1000000) * 10).substring(0, 6);

		OTP oldOtp = otpDao.findByMsisdn(msisdn);
		if (oldOtp != null) {
			if (isMaxOTPAttempt(oldOtp)) {
				throw new BusinessException(ResponseCode.MAX_OTP_ATTEMPT_REACHED);
			}
			oldAttempts = oldOtp.getAttempts();
			id = oldOtp.getId();
		}

		otp = otpDao.generateOTP(msisdn, otpString, uuid.toString(), id, oldAttempts);
		try {
			messageBrokerService.sendMessage(msisdn,MessageFormat.format(masterConfigService.getValueByKey(ConfigConstants.OTP_SMS), otp.getOpt()));
		} catch (Exception e) {
			logger.error(e.getMessage() + e.getStackTrace());
			throw new BusinessException(ResponseCode.ERROR_MESSAGE_MESSAGE_SEND_FAILED);
		}
		otp.setOpt("");// Not sending otp Code to UI
		otp.setUserId(userId);
		return otp;
	}

}