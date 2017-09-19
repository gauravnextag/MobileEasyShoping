package com.mob.shopping.service.impl;

import com.mob.shopping.repository.RetailerDao;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.RetailerServices;
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

}
