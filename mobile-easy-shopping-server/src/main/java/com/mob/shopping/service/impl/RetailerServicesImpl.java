package com.mob.shopping.service.impl;

import com.mob.shopping.beans.request.RegistrationRequest;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.repository.RetailerDao;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.RetailerServices;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetailerServicesImpl implements RetailerServices {


    @Autowired
    MasterConfigService masterConfigService;

    @Autowired
    RetailerDao retailerDao;



    private static final Logger LOGGER = LoggerFactory.getLogger(RetailerServicesImpl.class);

    @Override
    public void register(RegistrationRequest registrationRequest) throws BusinessException {
        try {

            if(StringUtils.isEmpty(registrationRequest.getMsisdn()) || StringUtils.isEmpty(registrationRequest.getStoreName())
                    || registrationRequest.getDistributorId() == null || registrationRequest.getDistrictId() == null ){
                throw new BusinessException(ErrorConstants.ERROR_CODE, ErrorConstants.ERROR_MESSAGE_INVALID_REQUEST);
            }else {
                retailerDao.save(registrationRequest);
            }

        }catch (Exception e){

        }
    }
}
