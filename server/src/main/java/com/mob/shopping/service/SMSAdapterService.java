/**
 * @Ajay Mishra
 */
package com.mob.shopping.service;

import com.mob.shopping.exception.BaseApplicationException;

public interface SMSAdapterService {
	   public  boolean sendMessage(String msisdn, String message)throws BaseApplicationException;
}
