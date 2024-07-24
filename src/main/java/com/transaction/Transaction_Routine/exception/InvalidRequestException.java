package com.transaction.Transaction_Routine.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidRequestException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(InvalidRequestException.class);
    
	public InvalidRequestException(String message) {
        super(message);
        logger.error("InvalidRequestException: {}", message);
    }

}
