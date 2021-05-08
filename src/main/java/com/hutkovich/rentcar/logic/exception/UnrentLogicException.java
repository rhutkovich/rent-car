package com.hutkovich.rentcar.logic.exception;

public class UnrentLogicException extends Exception {

    private static final long serialVersionUID = -478286997214101926L;

    public UnrentLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnrentLogicException(String string) {
        super(string);
    }
}
