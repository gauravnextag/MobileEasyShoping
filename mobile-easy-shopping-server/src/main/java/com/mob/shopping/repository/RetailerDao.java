package com.mob.shopping.repository;

import com.mob.shopping.beans.request.RegistrationRequest;
import com.mob.shopping.exception.BaseApplicationException;

public interface RetailerDao {
    void save(RegistrationRequest registrationRequest) throws BaseApplicationException;
}
