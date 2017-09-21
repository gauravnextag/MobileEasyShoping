package com.mob.shopping.util;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.mob.shopping.constants.enums.ResponseCode;
import com.mob.shopping.exception.BaseApplicationException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.ReadOnlyJWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

public class AuthUtils {

  private static final Logger logger = Logger.getLogger(AuthUtils.class.getName());

  private static final JWSHeader JWT_HEADER = new JWSHeader(JWSAlgorithm.HS256);
  private static final String TOKEN_SECRET = "(:D`E-K=PsP5k2Wd";
  private static final String ISSUER = "http://www.mobile.com";

  public static String decodeToken(String authHeader) throws BaseApplicationException {
    return xor(decode(authHeader).getSubject().getBytes());
  }

  public static ReadOnlyJWTClaimsSet decode(String authHeader) throws BaseApplicationException {

    ReadOnlyJWTClaimsSet jwtClaimsSet = null;
    try {
      jwtClaimsSet = SignedJWT.parse(getSerializedToken(authHeader)).getJWTClaimsSet();
    } catch (ParseException e) {
      throw new BaseApplicationException(ResponseCode.INVALID_TOKEN);
    }
    Date expiryDate = jwtClaimsSet.getExpirationTime();
    Date now = DateTime.now().toDate();

    if (now.after(expiryDate)) {
      throw new BaseApplicationException(ResponseCode.TOKEN_EXPIRED);
    }
    return jwtClaimsSet;
  }


  public static String decodeTokenForResetPasswordLink(String authHeader)
      throws BaseApplicationException {
    return xor(decodeResetLink(authHeader).getSubject().getBytes());
  }

  public static ReadOnlyJWTClaimsSet decodeResetLink(String authHeader)
      throws BaseApplicationException {

    ReadOnlyJWTClaimsSet jwtClaimsSet = null;
    try {
      jwtClaimsSet = SignedJWT.parse(getSerializedToken(authHeader)).getJWTClaimsSet();
    } catch (ParseException e) {
      throw new BaseApplicationException(ResponseCode.INVALID_TOKEN);
    }
    return jwtClaimsSet;
  }



  public static String createToken(String subject) {

    JWTClaimsSet claim = new JWTClaimsSet();
    claim.setSubject(xor(subject.getBytes()));
    claim.setIssuer(ISSUER);
    claim.setIssueTime(DateTime.now().toDate());
    claim.setExpirationTime(DateTime.now().plusDays(2).toDate());
    JWSSigner signer = new MACSigner(TOKEN_SECRET);
    SignedJWT jwt = new SignedJWT(JWT_HEADER, claim);
    try {
      jwt.sign(signer);
    } catch (JOSEException e) {
      logger.error(e.getMessage(), e);
    }

    return jwt.serialize();
  }

  private static String getSerializedToken(String authHeader) {
    return authHeader;
  }

  private static String xor(final byte[] input) {
    final byte[] output = new byte[input.length];
    final byte[] secret = TOKEN_SECRET.getBytes();
    int spos = 0;
    for (int pos = 0; pos < input.length; ++pos) {
      output[pos] = (byte) (input[pos] ^ secret[spos]);
      spos += 1;
      if (spos >= secret.length) {
        spos = 0;
      }
    }
    return new String(output);
  }
  
  
  
  

}
