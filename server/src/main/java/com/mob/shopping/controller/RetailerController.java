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

import com.mob.shopping.beans.request.RegistrationRequest;
import com.mob.shopping.beans.request.RetailerDto;
import com.mob.shopping.entity.Retailer;
import com.mob.shopping.service.RetailerServices;
import com.mob.shopping.util.RestResponse;
import com.mob.shopping.util.RestUtils;

@RestController
@RequestMapping(value = "retailer")
public class RetailerController {

    private static final Logger logger = LoggerFactory.getLogger(RetailerController.class);

    @Autowired
    private RetailerServices retailerServices;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<RestResponse<Boolean>> getDistrict(@RequestBody RegistrationRequest registrationRequest) {        
    	String method = "[CONTROLLER] getDistrict>>>> RegistrationRequest :: "+registrationRequest.toString();
    	logger.info(method);
    	retailerServices.register(registrationRequest);
    	return RestUtils.successResponse(Boolean.TRUE);
    }

    @RequestMapping(value = "/retailer/{distributorId}", method = RequestMethod.GET)
    public @ResponseBody ResponseEntity<RestResponse<List<Retailer>>> getRetailer(@PathVariable Long distributorId) {        
    	String method = "[CONTROLLER] getRetailer>>>> distributorId :: "+distributorId;
    	logger.info(method);
    	return RestUtils.successResponse(retailerServices.get(distributorId));
    }
    
    @RequestMapping(value = "/action", method = RequestMethod.PUT)
    public @ResponseBody ResponseEntity<RestResponse<Boolean>> approve(@RequestBody RetailerDto retailerDto) {        
    	String method = "[CONTROLLER] getRetailer>>>> retailerDto :: "+retailerDto;
    	logger.info(method);
    	return RestUtils.successResponse(retailerServices.changeStatus(retailerDto));
    }


    
    
    
 
}
