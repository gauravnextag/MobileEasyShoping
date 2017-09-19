package com.mob.shopping.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.xml.XMLSerializer;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.UUID;

public class CommonUtils {

    public static String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }

    public static HttpHeaders getHttpEntityHeaders(final String username, final String password,
            final MediaType contentType) {

        return new HttpHeaders() {

            /** The Constant serialVersionUID. */
            private static final long serialVersionUID = 1L;

            {
                String auth = username + ":" + password;
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);
                set("Authorization", authHeader);
                setContentType(contentType);
            }
        };
    }

    /**
     * Convert byte array to string.
     *
     * @param arrayToConvert
     *            the array to convert
     * 
     * @return the string
     */
    public static String convertByteArrayToString(byte[] arrayToConvert) {
        if (arrayToConvert != null) {
            return Base64.encodeBase64String(arrayToConvert);
        }
        return null;
    }

    /**
     * Convert string to byte array.
     *
     * @param stringToConvert
     *            the string to convert
     * @return the byte[]
     */
    public static byte[] convertStringToByteArray(String stringToConvert) {
        if (StringUtils.isNotEmpty(stringToConvert)) {
            return Base64.decodeBase64(stringToConvert.getBytes());
        } else {
            return null;
        }

    }

    /**
     * Generate random number.
     *
     * @return the long
     */
    public static String generateRandomNumber() {
        UUID uuid = UUID.randomUUID();
        return Long.toString(Math.abs(uuid.getLeastSignificantBits() % 1000000) * 10000000).substring(0, 5);
    }

    /**
     * Generate random number.
     *
     * @return the long
     */
    public static String generateEsignRandomNumber() {
        Random r = new Random();
        int low = 1;
        int high = 1000000000;
        return Long.toString((r.nextInt(high - low) + low) + System.currentTimeMillis());
    }

    /**
     * Method returns XMLSerializer instance
     * 
     * @return XMLSerializer
     */
    public static XMLSerializer getXMLSerializer() {
        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setSkipNamespaces(true);
        xmlSerializer.setRemoveNamespacePrefixFromElements(true);
        return xmlSerializer;
    }

    public static String generateTxnId(String msisdn){

        return  "payBank" + msisdn + CommonUtils.generateEsignRandomNumber() + CommonUtils.generateRandomNumber();
    }

}
