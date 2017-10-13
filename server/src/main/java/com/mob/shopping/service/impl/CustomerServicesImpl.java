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
import com.mob.shopping.beans.request.CustomerDto;
import com.mob.shopping.constants.ConfigConstants;
import com.mob.shopping.constants.ErrorConstants;
import com.mob.shopping.constants.enums.DeliveryStatus;
import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.entity.Customer;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.repository.CustomerDao;
import com.mob.shopping.repository.OTPDao;
import com.mob.shopping.service.CustomerServices;
import com.mob.shopping.service.MasterConfigService;
import com.mob.shopping.service.SMSAdapterService;
import com.mob.shopping.util.CommonUtility;

@Service
public class CustomerServicesImpl implements CustomerServices {

	private static final Logger logger = LoggerFactory.getLogger(CustomerServicesImpl.class);

	@Autowired
	CustomerDao customerDao;

	@Autowired
    private OTPDao otpDao;
	
	@Autowired
	private MasterConfigService masterConfigService;
	
	@Autowired
	private SMSAdapterService messageBrokerService;
    

	@Override
	public Long save(CustomerDto customerDto) {
		validate(customerDto);

		Customer customerObj = customerDao.findByMsisdn(customerDto.getMsisdn());
		
		if(customerObj!=null){
			
			throw new BaseApplicationException(ResponseCode.CUSTOMER_ORDER_ALREADY_PLACED);
		}
		
		if(CommonUtility.isValidString(customerDto.getMsisdn())&&CommonUtility.isValidString(customerDto.getOtp()) ){
			OTPOperationDTO oTPOperationDTO = otpDao.verifyOTP(customerDto.getMsisdn(), customerDto.getOtp());
			if(!oTPOperationDTO.getOtpStatus().equalsIgnoreCase(ErrorConstants.OTP_VERIFICATION_SUCCESS)){
				throw new BaseApplicationException(ResponseCode.INVALID_OTP);
			}
		}else{
			throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
		}
		
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
		
		try{
			messageBrokerService.sendMessage(customerDto.getMsisdn(),masterConfigService.getValueByKey(ConfigConstants.ADD_CUSTOMER_SMS));
				
		}catch(Exception e){
			logger.error(e.getMessage()+e.getStackTrace());
		}
	
		return userId;
		
	}

	private void validate(CustomerDto customerDto) {

		if (!CommonUtility.isValidString(customerDto.getName()) || !CommonUtility.isValidString(customerDto.getMsisdn())
				|| customerDto.getMsisdn().length() < 10
				|| !CommonUtility.isValidInteger(customerDto.getNoOfDevices())) {
			String method = "[SERVICE] save> validate >>>> validation failed for customerDto :: "
					+ customerDto.toString();
			logger.error(method);
			throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
		}
	}

	@Override
	public Map<String, List<Customer>> get(Long retailerId) {

		if (!CommonUtility.isValidLong(retailerId)) {
			throw new BaseApplicationException(ResponseCode.RETAILOR_NOT_FOUND);
		}
		List<Customer> customers = customerDao.get(retailerId);
		Map<String, List<Customer>> customerMapByDate = new WeakHashMap<String, List<Customer>>();
		customers.forEach(customer1 -> {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");
			String date = simpleDateFormat.format(new Date(customer1.getCreatedDate().getTime()));
			if (!customerMapByDate.containsKey(date)) {
				LinkedList<Customer> temp = new LinkedList<Customer>();
				temp.add(customer1);
				customerMapByDate.put(date, temp);
			} else {
				customerMapByDate.get(date).add(customer1);
			}
		});
		return customerMapByDate;
	}

}
