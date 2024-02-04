package com.iishanto.easycontactfinderbackend.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoginCredentialVerificationFailureException extends RuntimeException{
    public LoginCredentialVerificationFailureException(String message){
        super(message);
    }
}
