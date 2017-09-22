/**
 * @Ajay Mishra
 */
package com.mob.shopping.repository;

import com.mob.shopping.entity.User;
import com.mob.shopping.exception.BaseApplicationException;

public interface UserDao {
	
	User findByMSISDN(String msisdn) throws BaseApplicationException;
	User findByUserIdAndMSISDN(Long userId,String msisdn) throws BaseApplicationException;

}
