package com.mob.shopping.repository;

import com.mob.shopping.entity.Retailer;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.exception.DaoException;

import java.util.List;

public interface    RetailerDao {
    void save(Retailer retailer) throws BaseApplicationException;

    List<Retailer> get(Long distributorId) throws BaseApplicationException;

    Retailer getPendingRetailerById(Long id);

    void update(Retailer retailer);

    Retailer findByMsisdnAndRegistrationStatus(String msisdn, Integer registrationStatus);

    boolean checkMsisdnStatus(String msisdn) throws DaoException;
}
