package com.mob.shopping.repository;

import com.mob.shopping.entity.Distributor;
import com.mob.shopping.exception.DaoException;

import java.util.List;

public interface DistributorDao {

    List getDistributors(Long districtId) throws DaoException;

    Distributor findByMsisdn(String msisdn) throws DaoException;
//
//    CheckBankStatusResponse findByMsisdnAndCafAndAppName(String msisdn , String caf, String appName) throws DaoException;
//
//    void save(Retailer bankTxnLog) throws DaoException;
}
