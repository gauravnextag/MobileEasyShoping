/**
 * @Ajay Mishra
 */
package com.mob.shopping.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.entity.User;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.repository.UserDao;
import com.mob.shopping.service.UserService;
import com.mob.shopping.util.CommonUtility;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public User findByMSISDN(String msisdn) throws BaseApplicationException {
		User user = userDao.findByMSISDN(msisdn);
		if (CommonUtility.isNullObject(user)) {
			throw new BaseApplicationException(ResponseCode.INVALID_USER);
		}
		return user;
	}

	@Override
	public User findByUserIdAndMSISDN(Long userId, String msisdn) throws BaseApplicationException {
		User user = userDao.findByUserIdAndMSISDN(userId, msisdn);
		if (CommonUtility.isNullObject(user)) {
			throw new BaseApplicationException(ResponseCode.INVALID_USER);
		}
		return user;
	}

	@Override
	public void save(String msisdn, Long userId, Integer userType) {
		if(StringUtils.isEmpty(msisdn) || userId ==null || userType ==null){
			throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
		}
		User user = new User();
		user.setMsisdn(msisdn);
		user.setUserId(userId);
		user.setUserType(userType);
		user.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		user.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
		userDao.save(user);

	}

}
