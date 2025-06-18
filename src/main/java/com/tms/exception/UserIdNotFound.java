package com.tms.exception;

public class UserIdNotFound extends ResourceNotFoundException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserIdNotFound(String message) {
		super(message);
	}
	
}
