package com.mob.shopping.repository;

import com.mob.shopping.entity.Customer;
import com.mob.shopping.exception.DaoException;

public interface CustomerDao {
  void save(Customer ustomer) throws DaoException;
}
