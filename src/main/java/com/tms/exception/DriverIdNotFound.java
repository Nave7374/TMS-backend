package com.tms.exception;

public class DriverIdNotFound extends ResourceNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DriverIdNotFound(String message) {
		super(message);
	}
}