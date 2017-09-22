package com.mob.shopping.service;


import java.util.List;

import com.mob.shopping.beans.request.RegistrationRequest;
import com.mob.shopping.beans.request.RetailerDto;
import com.mob.shopping.entity.Retailer;
import com.mob.shopping.exception.BaseApplicationException;

public interface RetailerServices {

    void register(RegistrationRequest registrationRequest) throws BaseApplicationException;

    List<Retailer> get(Long distributorId)throws BaseApplicationException;
    
    Boolean changeStatus(RetailerDto retailerDto)throws BaseApplicationException;
}
