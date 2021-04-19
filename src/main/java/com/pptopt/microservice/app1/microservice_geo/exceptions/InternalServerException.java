package com.pptopt.microservice.app1.microservice_geo.exceptions;

import java.io.Serializable;

public class InternalServerException extends RuntimeException implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -589314097756741714L;

	/**
	 * 
	 */

	public InternalServerException(String message) {
        super(message);
    }

	
}
