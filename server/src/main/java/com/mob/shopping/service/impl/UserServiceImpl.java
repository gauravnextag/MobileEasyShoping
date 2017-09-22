/**
 * @Ajay Mishra
 */
package com.mob.shopping.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.entity.User;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.repository.UserDao;
import com.mob.shopping.service.UserService;
import com.mob.shopping.util.CommonUtility;

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

}
