package com.mob.shopping.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mob.shopping.beans.request.DistrictRequest;
import com.mob.shopping.entity.District;
import com.mob.shopping.entity.State;
import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.repository.RegionDao;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.RegionServices;
import com.mob.shopping.util.CommonUtility;

@Service
public class RegionServicesImpl implements RegionServices{


    @Autowired
    MasterConfigService masterConfigService;

    @Autowired
    RegionDao regionDao;



    private static final Logger LOGGER = LoggerFactory.getLogger(RegionServicesImpl.class);

        @Override
        public List<State> getStates() throws BusinessException{
                return regionDao.getStates();
        }

    @Override
    public List<District> getDistrictRequest(Long stateId) throws BaseApplicationException {

    	if(!CommonUtility.isValidLong(stateId)){
    		throw new  BaseApplicationException(ResponseCode.INVALID_PARAMETER);
    	}
    	return regionDao.getDistricts(stateId);
    }

}
