/**
 * @Ajay Mishra
 */
package com.mob.shopping.util;

public interface Constants {
	Integer SUCCESS_STATUS = 200;
	Integer FAILURE_STATUS = 101;
	String  ID = "id";
	String  IS_DELETED = "isDeleted";
	String  MSISDN   = "msisdn";
	String USER_ID   = "userId";
	String STATE_ID = "stateId";
	String NAME = "name";


	public interface DISTRIBUTOR {
		String DISTRICT_ID = "districtId";
		String RETAILER_COUNT = "retailerCount";

	}
	
	
	public interface RETAILER {
		String DISTRIBUTOR_ID = "distributorId";
		String REGISTRATION_STATUS = "registrationStatus";
	}

	public interface Headers {
		String AUTH_TOKEN = "Authorization";
	    String KEY="KEY";
		String DATA = "DATA";
		String USER_ID = "USER-ID";
	}
	
	public interface Customer {
		String RETAILER_ID = "retailerID";
	}
	
	

}
