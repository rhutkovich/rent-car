package com.epam.rentcar.logic.exception;

public class BusinessLogicException extends Exception {
    private static final long serialVersionUID = -6873139160464594129L;

    public BusinessLogicException(String message, Throwable cause) {
	super(message, cause);
    }

    public BusinessLogicException(String string) {
	super(string);
    }
}
