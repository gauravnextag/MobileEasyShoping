package com.mob.shopping.interceptor;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.security.GeneralSecurityException;

import javax.naming.AuthenticationException;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.exception.BaseApplicationException;
import com.mob.shopping.security.EdCipher;
import com.mob.shopping.security.EdCipherData;
import com.mob.shopping.security.EncryptionRSA;
import com.mob.shopping.util.CommonUtility;
import com.mob.shopping.util.Constants;

/**
 * @author Ashish Gupta
 */

public class ServletRequestWrapper extends HttpServletRequestWrapper {

	private final String payload;

	private static final Logger logger = LoggerFactory.getLogger(ServletRequestWrapper.class);
	
	private String encryptedKey = null;
	
	public ServletRequestWrapper(HttpServletRequest request) throws AuthenticationException, JSONException, IOException, GeneralSecurityException {
		super(request);
		StringBuilder stringBuilder = new StringBuilder();
		String userId = request.getHeader(Constants.Headers.USER_ID);
		logger.info("Decryption Logic starting for userId :: ",userId);
		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				StringWriter stringWriter = new StringWriter();
				IOUtils.copy(inputStream, stringWriter, "UTF-8");
				stringBuilder.append(stringWriter.toString());
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw new AuthenticationException("Error reading the request decrypted payload");
		}
		logger.info("Request Before modification :: " + stringBuilder.toString());
		
		String decrytedData = "";
		if (CommonUtility.isValidString(stringBuilder.toString())) {
			JSONObject jsonObject = new JSONObject(stringBuilder.toString());
			encryptedKey = jsonObject.getString(Constants.Headers.KEY);
			String encryptedData = jsonObject.getString(Constants.Headers.DATA);
			
			if(!CommonUtility.isValidString(encryptedKey)||!CommonUtility.isValidString(encryptedData)){
				throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
			}
			
			decrytedData = dataDecrypt(Base64.decodeBase64(encryptedData), encryptedKey, userId);
		}
		logger.info("Request After modification :: " + decrytedData);
		payload = decrytedData;
	}
	
	public String encryptedKey() {
		return encryptedKey;
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(payload.getBytes());
		ServletInputStream inputStream = new ServletInputStream() {
			public int read()
					throws IOException {
				return byteArrayInputStream.read();
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener arg0) {
				// TODO Auto-generated method stub
			}
		};
		return inputStream;
	}

	public String getPayload() {
		return payload;
	}

	public String dataDecrypt(byte[] bs, String encryptedKey, String userId) throws BaseApplicationException, IOException, GeneralSecurityException {
		logger.info("data decrypt method entered :: user ID :: ",userId);
		EncryptionRSA encryptionRSA = new EncryptionRSA();
		EdCipher edCipher = new EdCipher();
		//String secretKeyDecrypted = encryptionRSA.decrypt(encryptedKey, "/home/appuser/properties/yatra/server_private.der","","");
		String secretKeyDecrypted = encryptionRSA.decrypt(encryptedKey, "/Users/b0200200/Desktop/test/mob-shopping","","");

	
		logger.info("secret Key after Decryption :: "+ secretKeyDecrypted);
		String[] secretKey = secretKeyDecrypted.split(userId.trim());
		if (secretKey.length < 2) {
			logger.error("Secret key could not be split by userID :: ",userId," secret key :: ",secretKey);
			throw new BaseApplicationException(ResponseCode.GENRAL_ERROR);
		}
		EdCipherData edCipherData = new EdCipherData();
		edCipherData.setData(bs);
		edCipherData.setPassKey(secretKey[0]);
		edCipherData.setSalt(Base64.decodeBase64(secretKey[1]));
		edCipherData.setIv(Base64.decodeBase64(secretKey[2]));
		logger.info("data decrypt method exiting :: user ID :: ",userId);
		return edCipher.decrypt(edCipherData);
	}
	
//	public static void main(String[] args) {
//		String decrytedData = "";
//			String encryptedKey = "";
//			String encryptedData = "";
//			
//			if(!CommonUtility.isValidString(encryptedKey)||!CommonUtility.isValidString(encryptedData)){
//				throw new BaseApplicationException(ResponseCode.INVALID_PARAMETER);
//			}
//			
//			 decrytedData = dataDecrypt(Base64.decodeBase64(encryptedData), encryptedKey, "userId");
//		}
//	}

	
	
	
}