package com.mob.shopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mob.shopping.beans.request.DistributorListRequest;
import com.mob.shopping.entity.Distributor;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.service.DistributorServices;
import com.mob.shopping.util.RestResponse;
import com.mob.shopping.util.RestUtils;

@RestController
public class DistributorController {

    private static final Logger logger = LoggerFactory.getLogger(DistributorController.class);

    @Autowired
    private DistributorServices distributorServices;


    @RequestMapping(value = "/getDistributors", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity<RestResponse<List<Distributor>>> getDistributors(@RequestBody DistributorListRequest distributorListRequest)
    throws BaseApplicationException{
    	String method = "[CONTROLLER] getDistributors>>>> DistributorListRequest :: "+distributorListRequest.toString();
    	logger.info(method);
    	return RestUtils.successResponse(distributorServices.getDistributors(distributorListRequest));
    }


}