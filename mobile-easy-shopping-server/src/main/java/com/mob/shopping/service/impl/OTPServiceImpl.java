package com.mob.shopping.service.impl;

import com.mob.shopping.constants.ConfigConstants;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.entity.OTP;
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

	private final Logger LOGGER = LoggerFactory.getLogger(OTPServiceImpl.class);


	@Autowired
	private OTPDao otpDao;

    @Autowired
    MasterConfigService masterConfigService;

	@Autowired
	private MessageBrokerService messageBrokerService;


//	public OTPOperationDTO verifyOTP(String msisdn, String otp, String otpToken) {
//
//		OTPOperationDTO otpOperationDTO = null;
//		otpOperationDTO = dslOtpDao.verifyOTP(msisdn, otp, otpToken);
//
//		return otpOperationDTO;
//	}
//
//	public OTPOperationDTO verifyOTPFromCache(DSLBean cached, DSLBean original) {
//
//		OTPOperationDTO otpOperationDTO = null;
//		if (cached.getDslOtp().getOtpCode().equals(original.getDslOtp().getOtpCode()) && cached.getDslOtp().getOtpToken().equals(original.getDslOtp().getOtpToken())) {
//			otpOperationDTO = new OTPOperationDTO(CAFConstants.OTP_VERIFICATION_SUCCESS);
//		} else {
//			new OTPOperationDTO(CAFConstants.ERROR_OTP_VERIFICATION_FAILED);
//		}
//		return otpOperationDTO;
//	}

	public boolean isMaxOTPAttempt(String msisdn) {
		if (org.apache.commons.lang.StringUtils.isEmpty(msisdn)) {
			return true;
		}
		return false;
	}

	public OTP generateOTP(String msisdn) throws BusinessException {

		OTP otp = null;
		try {
			UUID uuid = UUID.randomUUID();
			String otpString = Long.toString(Math.abs(uuid.getLeastSignificantBits() % 1000000) * 10).substring(0,6);

			otp = otpDao.generateOTP(msisdn, otpString, uuid.toString());

			messageBrokerService.sendMessage(msisdn, masterConfigService.getValueByKey(ConfigConstants.OTP_SHORT_CODE),
					MessageFormat.format(masterConfigService.getValueByKey(ConfigConstants.OTP_SMS), otp.getOpt()));

		} catch (final Exception e) {
			LOGGER.error("exception occured while sending OTP ", e);
			throw new BusinessException(ErrorConstants.ERROR_CODE, ErrorConstants.ERROR_GENERATE_OTP);
		}

		return otp;
	}


//	public DSLBean getDetailsByDslId(String dslId,DSLBean dslBean) throws AdapterException, JsonParseException, JsonMappingException, IOException, BusinessException, JAXBException, XMLStreamException {
//		if (dslId == null) {
//			throw new BusinessException(CAFConstants.ERROR_CODE_ERROR, CAFConstants.ERROR_MESSAGE_MOBILE_MANDATORY);
//		}
//		CustomerProfile customerProfile = prepaidAdapter.getConnectionTypeDetails(dslId,"dsl","tMedia");
//		dslBean.setMsisdn(customerProfile.getRtnNumber());
//		dslBean.setCustomerAddress(customerProfile.getCustomerAddress());
//		return dslBean;
//
//	}
}