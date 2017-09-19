package com.mob.shopping.constants;

public interface ErrorConstants {
    String ERROR_CODE = "error";
    String DAO_STATUS_CODE = "Dao Error";
    String ERROR_MESSAGE_INVALID_RESPONSE = "Invalid Response From Third Party";
    String ERROR_MESSAGE_CONNECTION_ERROR = "Error while communicating with Third Party";
    String ERROR_MESSAGE_INVALID_REQUEST= "Invalid Request Body";
    String ERROR_MESSAGE_DOWNSTREAM_SYSTEM_DOWN = "System is not responding. Please contact IT Helpdesk.";
    String ERROR_MESSAGE_CONIFG_KEY = "Key is required";
    String ERROR_MESSAGE_NOT_FOUND_IN_DB = "Key not found in DB";
    String ERROR_MESSAGE_DB_ERROR = "Error While Fetching Records From DB";
    String ERROR_MESSAGE_DB_SAVE_ERROR = "Error While saving TXN in DB";
    String ERROR_MESSAGE_DAO = "Error creating record. Please check the data and retry.";
    String ERROR_MESSAGE_CIRCLE_MANDATORY = "Please provide circle name.";
    String ERROR_CODE_MESSAGE = "An error has occured. Please contact the system administrator.";
    String ERROR_CODE_OPEN_BANK_ACCOUNT_MESSAGE = "Bank Account Creation Failed.";
    String BANK_ACCOUNT_CREATION_SUCCESS = "Successfully created bank account.";
    String ERROR_MESSAGE_SOCKET_TIMEOUT_BANK_ACCOUNT = "Timeout occured while bank account creation. Please retry.";
    String ERROR_MESSAGE_ENCRYPTION_FAILED = "Something went wrong whle encrypting data.";
    String ERROR_MESSAGE_REQUEST_TEMPERED = "Request data has been tampered";
    String ERROR_MESSAGE_REQUEST_XML_ERROR = "Error while creating request xml";
    String ERROR_MESSAGE_KEY_NOT_FOUND= "Key Not Found in cache";
    String ERROR_MESSAGE_PAYMENT_BANK_QUEUE_DOWN= "Payment Bank System Message queue down";



}
