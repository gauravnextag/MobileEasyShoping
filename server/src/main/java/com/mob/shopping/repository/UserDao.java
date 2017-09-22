/**
 * @Ajay Mishra
 */
package com.mob.shopping.repository;

import com.mob.shopping.entity.User;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.exception.DaoException;

public interface UserDao {
	
	User findByMSISDN(String msisdn) throws DaoException;
	User findByUserIdAndMSISDN(Long userId,String msisdn) throws DaoException;

    void save(User user) throws DaoException;
}
