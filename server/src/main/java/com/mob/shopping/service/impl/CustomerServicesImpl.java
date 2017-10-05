package com.mob.shopping.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mob.shopping.beans.request.CustomerDto;
import com.mob.shopping.constants.enums.DeliveryStatus;
import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.entity.Customer;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.repository.CustomerDao;
import com.mob.shopping.service.CustomerServices;
import com.mob.shopping.util.CommonUtility;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CustomerServicesImpl implements CustomerServices {
    
	private static final Logger logger = LoggerFactory.getLogger(CustomerServicesImpl.class);

    @Autowired
    CustomerDao customerDao;



	@Override
	public Long save(CustomerDto customerDto) {
		validate(customerDto);
		Customer customer = new Customer();
		customer.setName(customerDto.getName());
		customer.setMsisdn(customerDto.getMsisdn());
		customer.setRetailerID(customerDto.getRetailerId());
		customer.setNoOfDevices(customerDto.getNoOfDevices());
		customer.setDeliveryStatus(DeliveryStatus.PENDING.getValue());
		customer.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		customer.setLastModifiedDate(new Timestamp(System.currentTimeMillis()));
		Long userId =  customerDao.save(customer);
		if(!CommonUtility.isValidLong(userId)){
			throw new BaseApplicationException();
		}
		return userId;
		
	}
	
	private void validate(CustomerDto customerDto){
		
		if(!CommonUtility.isValidString(customerDto.getName()) || 
				!CommonUtility.isValidString(customerDto.getMsisdn()) ||
				customerDto.getMsisdn().length()<10 ||
				!CommonUtility.isValidInteger(customerDto.getNoOfDevices()) ){
			String method = "[SERVICE] save> validate >>>> validation failed for customerDto :: "+customerDto.toString();
	    	logger.error(method);
			throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
		}
	}

	@Override
	public List<Customer> get(Long retailerId) {
		
		if(!CommonUtility.isValidLong(retailerId)){
    		throw new BaseApplicationException(ResponseCode.RETAILOR_NOT_FOUND);
    	}
		return customerDao.get(retailerId);
	}

}
