package com.tms.exception;

public class VehicleIdNotFound extends ResourceNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VehicleIdNotFound(String message) {
		super(message);
	}
	
}
