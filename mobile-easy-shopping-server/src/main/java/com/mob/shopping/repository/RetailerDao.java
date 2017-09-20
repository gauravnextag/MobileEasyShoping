package com.mob.shopping.repository;

import com.mob.shopping.beans.request.RegistrationRequest;
import com.mob.shopping.exception.DaoException;

public interface RetailerDao {
    void save(RegistrationRequest registrationRequest) throws DaoException;
//
//    CheckBankStatusResponse findByMsisdnAndCafAndAppName(String msisdn , String caf, String appName) throws DaoException;
//
//    void save(Retailer bankTxnLog) throws DaoException;
}
