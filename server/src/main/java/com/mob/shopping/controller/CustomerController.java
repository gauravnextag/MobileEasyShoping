package com.mob.shopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mob.shopping.beans.request.CustomerDto;
import com.mob.shopping.entity.Customer;
import com.mob.shopping.service.CustomerServices;
import com.mob.shopping.util.RestResponse;
import com.mob.shopping.util.RestUtils;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    
    @Autowired
    private CustomerServices customerServices;
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<RestResponse<Long>> save(@RequestBody CustomerDto customerDto) {        
    	String method = "[CONTROLLER] save>>>> customerDto :: "+customerDto.toString();
    	logger.info(method);
    	return RestUtils.successResponse(customerServices.save(customerDto));
    }
    
    @RequestMapping(value = "/{retailerId}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<RestResponse<List<Customer>>> get(@PathVariable Long retailerId) {        
    	String method = "[CONTROLLER] get>>>> retailerId :: "+retailerId;
    	logger.info(method);
    	return RestUtils.successResponse(customerServices.get(retailerId));
    }
}
