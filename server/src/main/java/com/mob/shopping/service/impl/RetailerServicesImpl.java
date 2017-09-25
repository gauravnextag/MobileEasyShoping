package com.mob.shopping.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import com.mob.shopping.constants.enums.UserType;
import com.mob.shopping.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mob.shopping.beans.request.RetailerDto;
import com.mob.shopping.constants.enums.RegistrationStatus;
import com.mob.shopping.entity.Retailer;
import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.repository.RetailerDao;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.RetailerServices;
import com.mob.shopping.util.CommonUtility;

@Service
public class RetailerServicesImpl implements RetailerServices {

	@Autowired
	MasterConfigService masterConfigService;

	@Autowired
	RetailerDao retailerDao;

	@Autowired
	UserService userService;

	private static final Logger logger = LoggerFactory.getLogger(RetailerServicesImpl.class);

	@Override
    public void register(RetailerDto registrationRequest) throws BaseApplicationException {
		Retailer retailer = new Retailer();
		retailer.setDistrictId(registrationRequest.getDistrictId());
		retailer.setDistributorId(registrationRequest.getDistributorId());
		retailer.setStoreName(registrationRequest.getStoreName());
		retailer.setMsisdn(registrationRequest.getMsisdn());
		retailer.setRegistrationStatus(RegistrationStatus.PENDING.getValue());
		retailer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		retailer.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
		if(!CommonUtility.isValidString(registrationRequest.getMsisdn()) || !CommonUtility.isValidString(registrationRequest.getStoreName())
				|| !CommonUtility.isValidLong(registrationRequest.getDistrictId())){
			String method = "[SERVICE] register>>>>  :: Missing > Msisdn | StoreName | DistrictId ";
			logger.error(method);
			throw new BaseApplicationException(ResponseCode.GENRAL_ERROR);
		}
            retailerDao.save(retailer);
	}


	@Override
	public Map get(Long distributorId) throws BaseApplicationException {
		String method = "[SERVICE] get>>>>  :: distributorId > distributorId ";
    	logger.error(method);
    	List<Retailer> retailer =  retailerDao.get(distributorId);
    	if(CommonUtility.isNullObject(retailer)){
    		throw new BaseApplicationException(ResponseCode.RETAILOR_NOT_FOUND);
    	}
    	Map retailerMapByDate = new WeakHashMap<String,Retailer>();
    	retailer.forEach(retailer1 -> {
            System.out.println(retailer1.getCreatedDate().toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
            String date =simpleDateFormat.format(new Date(retailer1.getCreatedDate().getTime()));
            System.out.println(date);
            retailerMapByDate.put(date,retailer);
        });

    	return retailerMapByDate;
	}


	@Override
	public Boolean changeStatus(RetailerDto retailerDto) throws BaseApplicationException {
		if(!( (retailerDto.getRegistrationStatus() == RegistrationStatus.APPROVED.getValue()) ||
                (retailerDto.getRegistrationStatus() == RegistrationStatus.REJECTED.getValue()) )){
			String method = "[SERVICE] changeStatus>>>>  :: Invalid change status request  > retailerDto "+retailerDto.toString();
	    	logger.error(method);
	    	throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
		}
		
    Retailer retailer = retailerDao.getPendingRetailerById(retailerDto.getId());
    
    if((CommonUtility.isNullObject(retailer)) || (retailerDto.getDistributorId() != retailer.getDistributorId()) ){
    	String method = "[SERVICE] changeStatus>>>>  :: Invalid change status request  > retailerDto "+retailerDto.toString();
    	logger.error(method);
    	throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
    }
    retailer.setRegistrationStatus(retailerDto.getRegistrationStatus());
    if((retailerDto.getRegistrationStatus() == RegistrationStatus.APPROVED.getValue())){
    	userService.save(retailer.getMsisdn(),retailer.getId(), UserType.RETAILER.getValue());
	}
     retailerDao.update(retailer);
     return Boolean.TRUE;
	}
	
	
}
