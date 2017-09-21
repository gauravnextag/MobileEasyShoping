package com.mob.shopping.repository;

import com.mob.shopping.beans.OTPOperationDTO;
import com.mob.shopping.entity.OTP;
import com.mob.shopping.exception.DaoException;


public interface OTPDao {

	OTPOperationDTO verifyOTP(String msisdn, String otp	);

	OTP generateOTP(String msisdn, String otpCode, String uuid, Long id, Long oldAttempts) throws DaoException;

	OTP findByMsisdn(String msisdn);
}