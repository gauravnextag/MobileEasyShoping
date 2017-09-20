package com.mob.shopping.service.impl;

import com.mob.shopping.beans.request.DistrictRequest;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.exception.DaoException;
import com.mob.shopping.repository.RegionDao;
import com.mob.shopping.repository.RetailerDao;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.RegionServices;
import com.mob.shopping.service.RetailerServices;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.Bus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionServicesImpl implements RegionServices{


    @Autowired
    MasterConfigService masterConfigService;

    @Autowired
    RegionDao regionDao;



    private static final Logger LOGGER = LoggerFactory.getLogger(RegionServicesImpl.class);

        @Override
        public List getStates() throws BusinessException{
            try {
                return regionDao.getStates();
            }catch (Exception e){

            }
            return null;
        }

    @Override
    public List getDistrictRequest(DistrictRequest districtRequest) throws BusinessException {

            try {

                if(StringUtils.isEmpty(districtRequest.getStateId())){
                    throw new BusinessException(ErrorConstants.ERROR_CODE, ErrorConstants.ERROR_MESSAGE_INVALID_REQUEST);
                }else {
                    return regionDao.getDistricts(districtRequest.getStateId());
                }

            }catch (Exception e){

            }
        return null;
    }

}
