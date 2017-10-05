package com.mob.shopping.repository;

import java.util.List;

import com.mob.shopping.entity.Customer;

public interface CustomerDao {
  Long save(Customer ustomer);
  List<Customer>get(Long retailerId);
}
