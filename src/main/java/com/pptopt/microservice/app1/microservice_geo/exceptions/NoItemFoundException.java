package com.pptopt.microservice.app1.microservice_geo.exceptions;

import java.io.Serializable;

public class NoItemFoundException extends RuntimeException implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3334595436271080929L;

	public NoItemFoundException(String message) {
        super(String.format("Item not found. Id requested: '%s'", message));
        
    }

	
}
