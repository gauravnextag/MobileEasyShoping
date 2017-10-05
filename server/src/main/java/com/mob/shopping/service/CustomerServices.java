package com.mob.shopping.service;

import java.util.List;
import java.util.Map;

import com.mob.shopping.beans.request.CustomerDto;
import com.mob.shopping.entity.Customer;

public interface CustomerServices {
 Long  save(CustomerDto customerDto);
 Map<String,List<Customer>> get(Long retailerId);

}
