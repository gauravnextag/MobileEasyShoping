package com.mob.shopping.constants;

public interface ConfigConstants {

    String JEDIS_MAX_TOTAL = "jedis.max.total";
    String JEDIS_MIN_IDLE = "jedis.min.idle";
    String JEDIS_MAX_IDLE = "jedis.max.idle";
    String REDIS_IP_PROPERTY = "redis.ip.property";
    String REDIS_PORT_PROPERTY = "redis.port.property";
    String REDIS_SENTINEL_MASTER = "redis.sentinel.master";
    String REDIS_SENTINEL_HOST_PORTS = "redis.sentinel.host.and.ports";

    public static String OTP_ATTEMP_EXHAUST_MESSAGE = "otp.attempt.exhaust.message";
    public static String MAX_OTP_INTERVAL = "otp.attempt.interval";
    public static String MAX_OTP_ATTEMPT = "max.otp.attempt";
    public static String OTP_LENGTH = "otp.length";
    public static String OTP_SHORT_CODE = "otp.short.code";
    public static String MESSAGE_BROKER_URL = "message.broker.url";
    public static String MESSAGE_BROKER_USER_NAME = "message.broker.username";
    public static String MESSAGE_BROKER_PASSWORD = "message.broker.password";
    
    public static String OTP_SMS = "otp.sms.text";
    public static String ADD_CUSTOMER_SMS = "customer.add.sms.text";
    public static String ADD_RETAILER_SMS = "retailer.add.sms.text";
    public static String ADD_RETAILER_TO_DISTRIBUTOR_SMS = "retailer.add.distributor.sms.text";
    public static String RETAILER_APPROVED_SMS = "retailer.approved.sms.text";
    public static String RETAILER_REJECTED_SMS = "retailer.rejected.sms.text";




}
