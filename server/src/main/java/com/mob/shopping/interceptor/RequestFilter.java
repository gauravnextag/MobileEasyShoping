package com.mob.shopping.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;

import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.util.AuthUtils;
import com.mob.shopping.util.CommonUtility;
import com.mob.shopping.util.Constants;

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
		logger.info("Filer in process");
		// if (CommonUtility.isNullObject(yatraConstants)) {
		// cacheService = WebApplicationContextUtils
		// .getRequiredWebApplicationContext(this.getFilterConfig().getServletContext())
		// .getBean(CacheService.class);
		// }
		//
		String tokenParsed = AuthUtils.decodeToken(request.getHeader(Constants.Headers.AUTH_TOKEN));

		if (!CommonUtility.isValidString(tokenParsed)) {
			throw new BaseApplicationException(ResponseCode.INVALID_TOKEN);
		}

		//


		if (!request.getMethod().equals(RequestMethod.GET)) {
			// YatraServletRequestWrapper myrequest = new
			// YatraServletRequestWrapper(request);
			// requestFlooding.preventFalseRequest(myrequest.encryptedKey());
			filterChain.doFilter(request, response);
		} else {
			filterChain.doFilter(request, response);
		}

	}
}
