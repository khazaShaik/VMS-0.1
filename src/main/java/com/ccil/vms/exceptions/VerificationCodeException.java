package com.ccil.vms.exceptions;

/**
 * Exception modeling when something went wrong during verifying the code in second step.
 */
public class VerificationCodeException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VerificationCodeException(String message) {
        super(message);
    }
}
