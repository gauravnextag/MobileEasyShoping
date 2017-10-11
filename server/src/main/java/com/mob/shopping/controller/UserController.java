/**
 * @Ajay Mishra
 */
package com.mob.shopping.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mob.shopping.beans.request.UserDto;
import com.mob.shopping.util.RestResponse;
import com.mob.shopping.util.RestUtils;

@RestController
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	  @RequestMapping(value = "/user", method = RequestMethod.GET,produces={"application/json"})
	    public  ResponseEntity<RestResponse<UserDto>> get(HttpServletRequest request) {        
	    	String method = "[CONTROLLER] get>>>> user :: ";
	    	logger.info(method);
	    	UserDto userDto = (UserDto)request.getAttribute("user");
	    	return RestUtils.successResponse(userDto);
	    }

}
