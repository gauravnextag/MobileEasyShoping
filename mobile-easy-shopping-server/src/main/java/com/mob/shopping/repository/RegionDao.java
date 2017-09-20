package com.mob.shopping.repository;

import com.mob.shopping.exception.DaoException;

import java.util.List;

public interface RegionDao {
   public List getStates() throws DaoException;

   public List getDistricts(String stateId) throws DaoException;
//
//    CheckBankStatusResponse findByMsisdnAndCafAndAppName(String msisdn , String caf, String appName) throws DaoException;
//
//    void save(Retailer bankTxnLog) throws DaoException;
}
