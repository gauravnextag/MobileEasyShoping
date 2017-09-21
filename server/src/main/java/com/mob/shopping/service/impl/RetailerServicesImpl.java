package com.mob.shopping.service.impl;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mob.shopping.beans.request.RegistrationRequest;
import com.mob.shopping.constants.enums.RetailerRegistrationStatus;
import com.mob.shopping.entity.Retailer;
import com.mob.shopping.enums.ResponseCode;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.repository.RetailerDao;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.RetailerServices;
import com.mob.shopping.util.CommonUtility;

@Service
public class RetailerServicesImpl implements RetailerServices {

	@Autowired
	MasterConfigService masterConfigService;

	@Autowired
	RetailerDao retailerDao;

	private static final Logger logger = LoggerFactory.getLogger(RetailerServicesImpl.class);

	@Override
    public void register(RegistrationRequest registrationRequest) throws BaseApplicationException {
		 Retailer retailer = new Retailer(); 
		 retailer.setDistrictId(registrationRequest.getDistrictId());
         retailer.setDistributorID(registrationRequest.getDistributorId());
         retailer.setStore_name(registrationRequest.getStoreName());
         retailer.setMsisdn(registrationRequest.getMsisdn());
         retailer.setRegistrationStatus(RetailerRegistrationStatus.PENDING.getValue());
         retailer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
         retailer.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));         
            if(!CommonUtility.isValidString(registrationRequest.getMsisdn()) || !CommonUtility.isValidString(registrationRequest.getStoreName())
                    || !CommonUtility.isValidLong(registrationRequest.getDistrictId())){
        		String method = "[SERVICE] register>>>>  :: Missing > Msisdn | StoreName | DistrictId ";
            	logger.error(method);
                throw new BaseApplicationException(ResponseCode.GENRAL_ERROR);
            }
            retailerDao.save(retailer);
	}
}
