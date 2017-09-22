package com.mob.shopping.repository;

import java.util.List;

import com.mob.shopping.entity.Retailer;
import com.mob.shopping.exception.BaseApplicationException;

public interface RetailerDao {
    void save(Retailer retailer) throws BaseApplicationException;
    
    List<Retailer> get(Long distributorId) throws BaseApplicationException;
    
     Retailer getPendingRetailerById(Long id);
     
     void update(Retailer retailer);
}
