package com.mob.shopping.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.entity.Distributor;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.repository.DistributorDao;
import com.mob.shopping.service.DistributorServices;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.util.CommonUtility;

@Service
public class DistributorServicesImpl implements DistributorServices {

    private static final Logger logger = LoggerFactory.getLogger(DistributorServicesImpl.class);

    @Autowired
    MasterConfigService masterConfigService;

    @Autowired
    DistributorDao distributorDao;



    @SuppressWarnings("unchecked")
	@Override
    public List<Distributor> getDistributors(Long districtId) throws BaseApplicationException {
    	if(!CommonUtility.isValidLong(districtId)){
    		logger.error("Invalid districtId "+districtId);
    		throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
    	}
    return distributorDao.getDistributors(districtId);
    }
}
