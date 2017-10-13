/**
 * @Ajay Mishra
 */
package com.mob.shopping.util;

public interface Constants {
	Integer SUCCESS_STATUS = 200;
	Integer FAILURE_STATUS = 101;
	String ID = "id";
	String IS_DELETED = "isDeleted";
	String MSISDN = "msisdn";
	String USER_ID = "userId";
	String STATE_ID = "stateId";
	String NAME = "name";
	String CLIENT_ID = "client_id";
	String CLIENT_SECRET = "client_secret";
	String GRANT_TYPE = "grant_type";
	String AUTHORIZATION = "Authorization";
	String REQUEST_ENCODING_UTF8 = "UTF-8";
	String BEARER = "Bearer";
	String MOBILE_PREFIX_INDIA = "91";

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
		String KEY = "KEY";
		String DATA = "DATA";
		String USER_ID = "USER-ID";
	}

	public interface Customer {
		String RETAILER_ID = "retailerID";
	}
	public interface HTTP_STATUS {
		int OK = 200;
		int CREATED = 201;
		int NO_CONTENT = 204;
		int BAD_REQUEST = 400;
		int UNAUTHORIZED = 401;
		int INTERNAL_SERVER_ERROR = 500;
		int RETAILER_INSERT_OK= 484;

		}
	public interface AIS {
		String GRANT_TYPE = "com.agile.lab.common.gateway.adapters.auth.client.credentials";
		String TOKEN = "token";
		public interface SMS {
			String BASE_ENDPOINT = "com.agile.lab.common.gateway.adapters.auth.sms.endpoint";
			String SMSC_ENDPOINT = "com.agile.lab.common.gateway.adapters.sms.endpoint";
			String USER = "com.agile.lab.common.gateway.adapters.auth.sms.user";
			String PASSWORD = "com.agile.lab.common.gateway.adapters.auth.sms.password";
			}

	}
}
