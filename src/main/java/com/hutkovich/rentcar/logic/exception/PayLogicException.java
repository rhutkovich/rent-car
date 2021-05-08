package com.hutkovich.rentcar.logic.exception;

public class PayLogicException extends Exception {
    private static final long serialVersionUID = -6873139160464594129L;

    public PayLogicException(String message, Throwable cause) {
	super(message, cause);
    }

    public PayLogicException(String string) {
	super(string);
    }
}
