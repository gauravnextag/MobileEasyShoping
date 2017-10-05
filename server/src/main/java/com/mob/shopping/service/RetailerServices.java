package com.mob.shopping.service;


import java.util.List;
import java.util.Map;

import com.mob.shopping.beans.request.RetailerDto;
import com.mob.shopping.entity.Retailer;
import com.mob.shopping.exception.BaseApplicationException;

public interface RetailerServices {

    void register(RetailerDto registrationRequest) throws BaseApplicationException;

    Map<String,List<Retailer>> get(Long distributorId)throws BaseApplicationException;
    
    Boolean changeStatus(RetailerDto retailerDto)throws BaseApplicationException;
}
