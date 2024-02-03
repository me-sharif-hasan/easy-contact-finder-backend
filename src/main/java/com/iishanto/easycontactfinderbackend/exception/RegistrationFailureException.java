package com.iishanto.easycontactfinderbackend.exception;

public class RegistrationFailureException extends RuntimeException{
    public RegistrationFailureException() {
    }

    public RegistrationFailureException(String message) {
        super(message);
    }

    public RegistrationFailureException(String message, Throwable cause) {
        super(message, cause);
    }

    public RegistrationFailureException(Throwable cause) {
        super(cause);
    }

    public RegistrationFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
