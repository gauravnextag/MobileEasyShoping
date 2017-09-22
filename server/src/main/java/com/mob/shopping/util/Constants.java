/**
 * @Ajay Mishra
 */
package com.mob.shopping.util;

public interface Constants {
	Integer SUCCESS_STATUS = 200;
	Integer FAILURE_STATUS = 101;
	String  ID = "id";
	String  IS_DELETED = "isDeleted";

	public interface DISTRIBUTOR {
		String DISTRICT_ID = "districtId";
	}
	
	
	public interface RETAILER {
		String DISTRIBUTOR_ID = "distributorID";
		String REGISTRATION_STATUS = "registrationStatus";
	}

	public interface Headers {
		String AUTH_TOKEN = "AUTH-TOKEN";
	    String KEY="KEY";
		String DATA = "DATA";
		String USER_ID = "USER-ID";
	}

}
