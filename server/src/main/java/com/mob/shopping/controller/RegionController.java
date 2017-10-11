package com.mob.shopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mob.shopping.entity.District;
import com.mob.shopping.entity.State;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.service.RegionServices;
import com.mob.shopping.util.RestResponse;
import com.mob.shopping.util.RestUtils;

@RestController
public class RegionController {

	private static final Logger logger = LoggerFactory.getLogger(RegionController.class);

    @Autowired
    private RegionServices regionServices;

    @RequestMapping(value = "/state", method = RequestMethod.GET,produces={"application/json"})
    public  ResponseEntity<RestResponse<List<State>>> getStates() throws BaseApplicationException {
		String method = "[CONTROLLER] getStates>>>> ::::";
    	logger.info(method);
		return RestUtils.successResponse(regionServices.getStates());
    }

    @RequestMapping(value = "/district/{stateId}", method = RequestMethod.GET,produces={"application/json"})
    public  ResponseEntity<RestResponse<List<District>>>  getDistrict(@PathVariable Long stateId)throws BaseApplicationException {
		String method = "[CONTROLLER] getDistrict>>>> stateId :: "+stateId;
    	logger.info(method);
    	return RestUtils.successResponse(regionServices.getDistrictRequest(stateId));
    }
}
