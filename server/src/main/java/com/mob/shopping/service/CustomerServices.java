package com.mob.shopping.service;

import java.util.List;

import com.mob.shopping.beans.request.CustomerDto;
import com.mob.shopping.entity.Customer;

public interface CustomerServices {
 Long  save(CustomerDto customerDto);
 List<Customer>get(Long retailerId);

}
