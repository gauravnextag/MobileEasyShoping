package com.mob.shopping.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mob.shopping.beans.request.DistributorListRequest;
import com.mob.shopping.exception.BusinessException;
import com.mob.shopping.repository.DistributorDao;
import com.mob.shopping.service.DistributorServices;
import com.mob.shopping.service.MasterConfigService;

@Service
public class DistributorServicesImpl implements DistributorServices {


    @Autowired
    MasterConfigService masterConfigService;

    @Autowired
    DistributorDao distributorDao;



    private static final Logger LOGGER = LoggerFactory.getLogger(DistributorServicesImpl.class);

    @Override
    public List getDistributors(DistributorListRequest distributorListRequest) throws BusinessException {
    return distributorDao.getDistributors(distributorListRequest.getDistrictId());
    }
}
