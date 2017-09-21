package com.mob.shopping.repository;

import com.mob.shopping.beans.OTPOperationDTO;
import com.mob.shopping.entity.OTP;
import com.mob.shopping.exception.DaoException;


public interface OTPDao {

	OTPOperationDTO verifyOTP(String msisdn, String otp	);

	boolean areOTPGenerationExhausted(String msisdn);

	OTP generateOTP(String msisdn, String otpCode, String uuid) throws DaoException;

}