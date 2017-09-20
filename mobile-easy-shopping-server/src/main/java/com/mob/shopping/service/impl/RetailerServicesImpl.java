package com.mob.shopping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mob.shopping.beans.request.RegistrationRequest;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(RetailerServicesImpl.class);

	@Override
    public void register(RegistrationRequest registrationRequest) throws BaseApplicationException {

            if(!CommonUtility.isValidString(registrationRequest.getMsisdn()) || !CommonUtility.isValidString(registrationRequest.getStoreName())
                    || !CommonUtility.isValidLong(registrationRequest.getDistrictId())){
                throw new BaseApplicationException(ResponseCode.GENRAL_ERROR);
            }
            
            retailerDao.save(registrationRequest);
	}
}
