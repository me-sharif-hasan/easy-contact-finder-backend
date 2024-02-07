package com.iishanto.easycontactfinderbackend.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class UserNotLoggedInException extends Exception {

    public UserNotLoggedInException(String message) {
        super(message);
    }
}
