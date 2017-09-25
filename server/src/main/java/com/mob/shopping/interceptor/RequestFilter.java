package com.mob.shopping.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mob.shopping.beans.request.UserDto;
import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.util.AuthUtils;
import com.mob.shopping.util.CommonUtility;
import com.mob.shopping.util.Constants;
import com.mob.shopping.util.RestResponse;
import com.mob.shopping.util.RestUtils;

/**
 * @author Ajay Mishra
 */

@WebFilter(urlPatterns = "/*")
public class RequestFilter extends OncePerRequestFilter {

	private final Logger logger = LoggerFactory.getLogger(RequestFilter.class.getName());

	// @Autowired
	// private YatraConstants yatraConstants;
	//

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			if (CommonUtility.isValidString(request.getHeader(Constants.Headers.AUTH_TOKEN))) {
				String tokenParsed = AuthUtils.decodeToken(request.getHeader(Constants.Headers.AUTH_TOKEN));
				String[] list = tokenParsed.split(":");
			
				if (!CommonUtility.isValidString(tokenParsed)||CommonUtility.isNullObject(list) || list.length<3) {
					throw new BaseApplicationException(ResponseCode.INVALID_TOKEN);
				}
				UserDto userDto = new UserDto();
		    	userDto.setId(Long.parseLong(list[0]));
		    	userDto.setMsisdn(list[1]);
		    	userDto.setUserType(Integer.parseInt(list[2]));
				request.setAttribute("user",userDto);
			}else{
				if (request.getRequestURL().toString().contains("login")) {
					filterChain.doFilter(request, response);
				} else {
					throw new BaseApplicationException(ResponseCode.INVALID_USER);
				}
			}

			

		} catch (BaseApplicationException e) {
			RestResponse<?> err = RestUtils.errorResponseEnum(e.getResponseCode());
			response.setStatus(HttpStatus.OK.value());
			response.setHeader("content-type", "application/json");
			response.getWriter().write(convertObjectToJson(err));
		} catch (Exception e) {
			RestResponse<?> err = RestUtils.errorResponseData(e.getMessage());
			response.setStatus(HttpStatus.OK.value());
			response.setHeader("content-type", "application/json");
			response.getWriter().write(convertObjectToJson(err));
			
		}

	}

	public String convertObjectToJson(Object object) throws JsonProcessingException {
		if (object == null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(object);
	}
}
