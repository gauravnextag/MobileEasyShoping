package com.mob.shopping.service;


import java.util.Map;

import com.mob.shopping.beans.request.RetailerDto;
import com.mob.shopping.exception.BaseApplicationException;

public interface RetailerServices {

    void register(RetailerDto registrationRequest) throws BaseApplicationException;

    Map get(Long distributorId)throws BaseApplicationException;
    
    Boolean changeStatus(RetailerDto retailerDto)throws BaseApplicationException;
}
