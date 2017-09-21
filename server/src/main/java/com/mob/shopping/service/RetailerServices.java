package com.mob.shopping.service;


import com.mob.shopping.beans.request.RegistrationRequest;
import com.mob.shopping.exception.BaseApplicationException;

public interface RetailerServices {

    void register(RegistrationRequest registrationRequest) throws BaseApplicationException;
}