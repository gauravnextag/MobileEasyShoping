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

import com.mob.shopping.beans.request.DistrictRequest;
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

    @RequestMapping(value = "/getStates", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<RestResponse<List<State>>> getStates() {
		String method = "[CONTROLLER] getStates>>>> ::::";
    	logger.info(method);
		return RestUtils.successResponse(regionServices.getStates());
    }

    @RequestMapping(value = "/getDistrict", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<RestResponse<List<District>>>  getDistrict(@RequestBody DistrictRequest districtRequest)throws BaseApplicationException {
		String method = "[CONTROLLER] getDistrict>>>> DistrictRequest :: "+districtRequest.toString();
    	logger.info(method);
    	return RestUtils.successResponse(regionServices.getDistrictRequest(districtRequest));
    }
}
