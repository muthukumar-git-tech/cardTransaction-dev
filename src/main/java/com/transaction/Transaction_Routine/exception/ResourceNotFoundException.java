package com.transaction.Transaction_Routine.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceNotFoundException extends RuntimeException {
    private static final Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);

	public ResourceNotFoundException(String message) {
        super(message);
        logger.error("ResourceNotFoundException: {}", message);
    }

}
