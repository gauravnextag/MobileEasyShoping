package com.mob.shopping.service.impl;

import com.mob.shopping.beans.OTPOperationDTO;
import com.mob.shopping.constants.ConfigConstants;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.entity.OTP;
import com.mob.shopping.enums.ResponseCode;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.repository.OTPDao;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.MessageBrokerService;
import com.mob.shopping.service.OTPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.UUID;

@Component("OTPServiceImpl")
public class OTPServiceImpl implements OTPService {

	private final Logger logger = LoggerFactory.getLogger(OTPServiceImpl.class);


	@Autowired
	private OTPDao otpDao;

    @Autowired
    MasterConfigService masterConfigService;

	@Autowired
	private MessageBrokerService messageBrokerService;


	public OTPOperationDTO verifyOTP(String msisdn, String otp) {

		OTPOperationDTO otpOperationDTO = null;
		otpOperationDTO = otpDao.verifyOTP(msisdn, otp);

		return otpOperationDTO;
	}

	public boolean isMaxOTPAttempt(OTP otp) {
        if (otp.getAttempts().intValue() >= Integer.parseInt(masterConfigService.
                getValueByKey(ConfigConstants.MAX_OTP_ATTEMPT))) {
            logger.info("areOTPGenerationExhausted is TRUE, maxOTPAttempts::" + masterConfigService.getValueByKey(ConfigConstants.MAX_OTP_ATTEMPT));
            return true;
        }

        return false;
	}

	public OTP generateOTP(String msisdn) throws BaseApplicationException {

		OTP otp = null;
	    Long oldAttempts = Long.valueOf(0);
	    Long id = null;
        UUID uuid = UUID.randomUUID();
        String otpString = Long.toString(Math.abs(uuid.getLeastSignificantBits() % 1000000) * 10).substring(0,6);
        OTP oldOtp = otpDao.findByMsisdn(msisdn);
        if(oldOtp!=null){
            if(isMaxOTPAttempt(oldOtp)){
                throw new BusinessException(ResponseCode.MAX_OTP_ATTEMPT_REACHED);
            }
            oldAttempts = oldOtp.getAttempts();
            id =oldOtp.getId();
        }
        otp = otpDao.generateOTP(msisdn, otpString, uuid.toString(),id,oldAttempts);
        messageBrokerService.sendMessage(msisdn, masterConfigService.getValueByKey(ConfigConstants.OTP_SHORT_CODE),
					MessageFormat.format(masterConfigService.getValueByKey(ConfigConstants.OTP_SMS), otp.getOpt()));

		return otp;
	}

}