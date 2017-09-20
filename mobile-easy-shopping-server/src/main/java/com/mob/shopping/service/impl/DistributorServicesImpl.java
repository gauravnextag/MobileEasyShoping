package com.mob.shopping.service.impl;

import com.mob.shopping.beans.request.DistributorListRequest;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.repository.DistributorDao;
import com.mob.shopping.repository.RetailerDao;
import com.mob.shopping.service.DistributorServices;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.RetailerServices;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistributorServicesImpl implements DistributorServices {


    @Autowired
    MasterConfigService masterConfigService;

    @Autowired
    DistributorDao distributorDao;



    private static final Logger LOGGER = LoggerFactory.getLogger(DistributorServicesImpl.class);

    @Override
    public List getDistributors(DistributorListRequest distributorListRequest) throws BusinessException {
        try {

            if(distributorListRequest.getDistrictId() ==null){
                throw new BusinessException(ErrorConstants.ERROR_CODE, ErrorConstants.ERROR_MESSAGE_INVALID_REQUEST);
            }else {
                    return distributorDao.getDistributors(distributorListRequest.getDistrictId());
            }

        }catch (Exception e){

        }
        return null;
    }
}
