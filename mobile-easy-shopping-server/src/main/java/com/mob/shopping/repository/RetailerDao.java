package com.mob.shopping.repository;

import com.mob.shopping.entity.Retailer;
import com.mob.shopping.exception.BaseApplicationException;

public interface RetailerDao {
    void save(Retailer retailer) throws BaseApplicationException;
}
