package com.mob.shopping.service.impl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mob.shopping.beans.OTPOperationDTO;
import com.mob.shopping.beans.request.RetailerDto;
import com.mob.shopping.constants.ConfigConstants;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.constants.enums.RegistrationStatus;
import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.constants.enums.UserType;
import com.mob.shopping.entity.Distributor;
import com.mob.shopping.entity.Retailer;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.repository.OTPDao;
import com.mob.shopping.repository.RetailerDao;
import com.mob.shopping.service.DistributorServices;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.MessageBrokerService;
import com.mob.shopping.service.RetailerServices;
import com.mob.shopping.service.UserService;
import com.mob.shopping.util.CommonUtility;

@Service
public class RetailerServicesImpl implements RetailerServices {

	@Autowired
	MasterConfigService masterConfigService;

	@Autowired
	RetailerDao retailerDao;

	@Autowired
	UserService userService;

	@Autowired
	DistributorServices distributorService;

	@Autowired
	OTPDao otpDao;

	@Autowired
	private MessageBrokerService messageBrokerService;

	private static final Logger logger = LoggerFactory.getLogger(RetailerServicesImpl.class);

	@Override
	public void register(RetailerDto registrationRequest) throws BaseApplicationException {
		if (CommonUtility.isValidString(registrationRequest.getMsisdn())
				&& CommonUtility.isValidString(registrationRequest.getOtp())) {
			OTPOperationDTO oTPOperationDTO = otpDao.verifyOTP(registrationRequest.getMsisdn(),
					registrationRequest.getOtp());
			if (!oTPOperationDTO.getOtpStatus().equalsIgnoreCase(ErrorConstants.OTP_VERIFICATION_SUCCESS)) {
				throw new BaseApplicationException(ResponseCode.INVALID_OTP);
			}
		} else {
			throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
		}
		Distributor distributor = distributorService.getById(registrationRequest.getDistributorId());

		Retailer retailer = new Retailer();
		retailer.setDistrictId(registrationRequest.getDistrictId());
		retailer.setDistributorId(registrationRequest.getDistributorId());
		retailer.setStoreName(registrationRequest.getStoreName());
		retailer.setMsisdn(registrationRequest.getMsisdn());
		retailer.setRegistrationStatus(RegistrationStatus.PENDING.getValue());
		retailer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		retailer.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
		retailer.setGstNumber(registrationRequest.getGstNumber());
		retailer.setAddress(registrationRequest.getAddress());
		retailer.setLapuNumber(registrationRequest.getLapuNumber());

		if (!CommonUtility.isValidString(registrationRequest.getStoreName())
				|| !CommonUtility.isValidLong(registrationRequest.getDistrictId())) {
			String method = "[SERVICE] register>>>>  :: Missing > Msisdn | StoreName | DistrictId ";
			logger.error(method);
			throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
		}
		if (retailerDao.checkMsisdnStatus(registrationRequest.getMsisdn())) {
			retailerDao.save(retailer);
		} else {	
			throw new BaseApplicationException(ResponseCode.ALREADY_REGISTER);
		}

		try {

			distributor.setRetailerCount(distributor.getRetailerCount()+1);
			distributorService.update(distributor);
			messageBrokerService.sendMessage(retailer.getMsisdn(),
					masterConfigService.getValueByKey(ConfigConstants.OTP_SHORT_CODE),
					masterConfigService.getValueByKey(ConfigConstants.ADD_RETAILER_SMS));

			messageBrokerService.sendMessage(distributor.getMsisdn(),
					masterConfigService.getValueByKey(ConfigConstants.OTP_SHORT_CODE),
					masterConfigService.getValueByKey(ConfigConstants.ADD_RETAILER_TO_DISTRIBUTOR_SMS));
		} catch (Exception e) {

		}
	}

	@Override
	public Map<String, List<Retailer>> get(Long distributorId) throws BaseApplicationException {
		String method = "[SERVICE] get>>>>  :: distributorId > distributorId ";
		logger.error(method);
		List<Retailer> retailer = retailerDao.get(distributorId);
		if (CommonUtility.isNullObject(retailer)) {
			throw new BaseApplicationException(ResponseCode.RETAILOR_NOT_FOUND);
		}
		Map<String, List<Retailer>> retailerMapByDate = new WeakHashMap<String, List<Retailer>>();
		retailer.forEach(retailer1 -> {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
			String date = simpleDateFormat.format(new Date(retailer1.getCreatedDate().getTime()));
			if (!retailerMapByDate.containsKey(date)) {
				LinkedList<Retailer> temp = new LinkedList<Retailer>();
				temp.add(retailer1);
				retailerMapByDate.put(date, temp);
			} else {
				retailerMapByDate.get(date).add(retailer1);
			}
		});

		return retailerMapByDate;
	}

	@Override
	public Boolean changeStatus(RetailerDto retailerDto) throws BaseApplicationException {
		if (!((retailerDto.getRegistrationStatus().intValue() == RegistrationStatus.APPROVED.getValue().intValue())
				|| (retailerDto.getRegistrationStatus().intValue() == RegistrationStatus.REJECTED.getValue().intValue()))) {
			String method = "[SERVICE] changeStatus>>>>  :: Invalid change status request  > retailerDto "
					+ retailerDto.toString();
			logger.error(method);
			throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
		}

		Retailer retailer = retailerDao.getPendingRetailerById(retailerDto.getId());

		if (CommonUtility.isNullObject(retailer)) {
			String method = "[SERVICE] changeStatus>>>>  :: Invalid change status request  > retailerDto "
					+ retailerDto.toString();
			logger.error(method);
			throw new BaseApplicationException(ResponseCode.NO_PENDING_USER);
		}

		if ((retailerDto.getDistributorId().longValue() != retailer.getDistributorId().longValue())) {
			String method = "[SERVICE] changeStatus>>>>  :: Invalid change status request  > retailerDto "
					+ retailerDto.toString();
			logger.error(method);
			throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
		}
		retailer.setRegistrationStatus(retailerDto.getRegistrationStatus());
		if ((retailerDto.getRegistrationStatus().intValue() == RegistrationStatus.APPROVED.getValue().intValue())) {
			userService.save(retailer.getMsisdn(), retailer.getId(), UserType.RETAILER.getValue());
		}
		retailerDao.update(retailer);

		try {
			String smsText = null;
			if (retailerDto.getRegistrationStatus() == RegistrationStatus.APPROVED.getValue()) {
				smsText = masterConfigService.getValueByKey(ConfigConstants.RETAILER_APPROVED_SMS);
			} else if (retailerDto.getRegistrationStatus() == RegistrationStatus.REJECTED.getValue()) {
				smsText = masterConfigService.getValueByKey(ConfigConstants.RETAILER_REJECTED_SMS);
			}

			if (smsText != null) {
				messageBrokerService.sendMessage(retailer.getMsisdn(),
						masterConfigService.getValueByKey(ConfigConstants.OTP_SHORT_CODE), smsText);
			}

		} catch (Exception e) {
			logger.error(e.getMessage()+e.getStackTrace());

		}
		return Boolean.TRUE;
	}

}
