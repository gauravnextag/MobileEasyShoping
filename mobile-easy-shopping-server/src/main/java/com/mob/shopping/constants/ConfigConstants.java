package com.mob.shopping.constants;

public interface ConfigConstants {
    String BANK_SERVER_URL = "bank.server.url";
    String BANK_SERVER_UCID = "bank.server.ucid";
    String BANK_SERVER_CHECK_BALANCE_READ_TIMEOUT = "bank.server.check.balance.read.timeout";
    String BANK_SERVER_CHECK_BALANCE_CONNECTION_TIMEOUT = "bank.server.check.balance.connection.timeout";
    String JEDIS_MAX_TOTAL = "jedis.max.total";
    String JEDIS_MIN_IDLE = "jedis.min.idle";
    String JEDIS_MAX_IDLE = "jedis.max.idle";
    String REDIS_IP_PROPERTY = "redis.ip.property";
    String REDIS_PORT_PROPERTY = "redis.port.property";
    String REDIS_SENTINEL_MASTER = "redis.sentinel.master";
    String REDIS_SENTINEL_HOST_PORTS = "redis.sentinel.host.and.ports";

    String SOLACE_PAYMENT_BANK_CONNECTION_FACTORY = "solace.pb.connection.factory";
    String SOLACE_PAYMENT_BANK_URL = "solace.pb.url";
    String SOLACE_PAYMENT_USERNAME = "solace.pb.username";
    String SOLACE_PAYMENT_PASSWORD = "solace.pb.password";
    String SOLCAE_PAYMENT_BANK_TOPIC = "solcae.pb.topic";
    String PAYBANK_BLOCK_MESSAGE = "check.ret.block.message";


}
