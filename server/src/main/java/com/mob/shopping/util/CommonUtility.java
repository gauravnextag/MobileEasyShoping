package com.mob.shopping.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtility {
	private final static Logger logger = LoggerFactory.getLogger(CommonUtility.class.getName());

	public static boolean isNullObject(Object obj) {
		return (null == obj ? true : false);
	}

	public static boolean isValidString(String obj) {
		return ((null == obj || obj.trim().isEmpty()) ? false : true);
	}

	public static boolean isValidCollection(Collection<?> obj) {
		return (obj != null && obj.size() > 0 ? true : false);
	}

	public static double getScaledAmount(double amount) {
		BigDecimal scaledAmount = new BigDecimal(amount);
		scaledAmount = scaledAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return scaledAmount.doubleValue();
	}

	public static boolean isValidInteger(Integer value) {
		return ((null == value || value.intValue() == 0) ? false : true);
	}

	public static boolean isValidDouble(Double value){
		return ((null == value) ? false : true);
	}
	
	public static boolean isValidFloat(Float value){
		return ((null == value) ? false : true);
	}
	
	public static boolean isValidLong(Long value){
		return ((null == value || value.intValue() == 0) ? false : true);
	}

	public static boolean isValidList(List<?> list) {
		return ((list == null || list.size() == 0) ? false : true);
	}

	public static void setProxy() {
		final String proxyURL = "northproxy.airtelworld.in";
		final String proxyPort = "4145";
		System.setProperty("http.proxyHost", proxyURL);
		System.setProperty("http.proxyPort", proxyPort);
		System.setProperty("https.proxyHost", proxyURL);
		System.setProperty("https.proxyPort", proxyPort);		
		System.setProperty("https.proxyUser", "B0096766");
		System.setProperty("https.proxyPassword", "sunrajakash3!");
		System.setProperty("http.proxyUser", "B0096766");
		System.setProperty("http.proxyPassword", "sunrajakash3!");
	}

	public static void removeProxy() {
		System.setProperty("http.proxyHost", "");
		System.setProperty("http.proxyPort", "");
		System.setProperty("https.proxyHost", "");
		System.setProperty("https.proxyPort", "");
		System.setProperty("https.proxyUser", "");
		System.setProperty("https.proxyPassword", "");
		System.setProperty("http.proxyUser", "");
		System.setProperty("http.proxyPassword", "");
	}

	public static boolean isNotEmpty(String attrKey) {
		if(!attrKey.isEmpty()) 
			return true;
		return false;
	}
	
	public static boolean closeSession(String method, Session session) {
		logger.info(method + "Session close :: begins...");
		if (null != session) {
			session.close();
		}
		logger.info(method + "Session close :: ends...");
		return true;
	}
	
	
}
