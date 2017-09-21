package com.mob.shopping.service.impl;

import com.mob.shopping.beans.OTPOperationDTO;
import com.mob.shopping.constants.ConfigConstants;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.entity.OTP;
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

	private final Logger LOGGER = LoggerFactory.getLogger(OTPServiceImpl.class);


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

	public boolean isMaxOTPAttempt(String msisdn) {
		if (org.apache.commons.lang.StringUtils.isEmpty(msisdn)) {
			return true;
		}
		return false;
	}

	public OTP generateOTP(String msisdn) throws BaseApplicationException {

		OTP otp = null;
        UUID uuid = UUID.randomUUID();
        String otpString = Long.toString(Math.abs(uuid.getLeastSignificantBits() % 1000000) * 10).substring(0,6);

        otp = otpDao.generateOTP(msisdn, otpString, uuid.toString());
        messageBrokerService.sendMessage(msisdn, masterConfigService.getValueByKey(ConfigConstants.OTP_SHORT_CODE),
					MessageFormat.format(masterConfigService.getValueByKey(ConfigConstants.OTP_SMS), otp.getOpt()));

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