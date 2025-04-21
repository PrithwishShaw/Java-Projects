package com.sportsmanagement.exception;

public class TeamNameAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TeamNameAlreadyExistsException(String message) {
		super(message);
	}

}
