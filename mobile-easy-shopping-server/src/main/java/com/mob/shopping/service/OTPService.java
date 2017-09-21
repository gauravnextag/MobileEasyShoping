/**
 * 
 */
package com.mob.shopping.service;

import com.mob.shopping.beans.OTPOperationDTO;
import com.mob.shopping.entity.OTP;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.exception.BusinessException;
import org.springframework.stereotype.Service;


@Service
public interface OTPService {

//	DSLBean getDetailsByDslId(String dslId, DSLBean dslBean) throws AdapterException, JsonParseException, JsonMappingException, IOException, BusinessException, JAXBException, XMLStreamException;
//
	OTPOperationDTO verifyOTP(String msisdn, String otp);
//
//	OTPOperationDTO verifyOTPFromCache(DSLBean cached, DSLBean original);

	boolean isMaxOTPAttempt(String msisdn);

	OTP generateOTP(String msisdn) throws BaseApplicationException;

}
