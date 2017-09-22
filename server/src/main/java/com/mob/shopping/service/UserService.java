/**
 * @Ajay Mishra
 */
package com.mob.shopping.service;

import com.mob.shopping.entity.User;
import com.mob.shopping.exception.BaseApplicationException;

public interface UserService {
	User findByMSISDN(String msisdn) throws BaseApplicationException;
	User findByUserIdAndMSISDN(Long userId,String msisdn) throws BaseApplicationException;


}
