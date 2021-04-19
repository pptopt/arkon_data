package com.pptopt.microservice.app1.microservice_geo.exceptions;

import java.io.Serializable;

public class ValidationException extends RuntimeException implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7870867496614897269L;

	public ValidationException(String message) {
        super(message);
    }

	
}

