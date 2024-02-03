package com.iishanto.easycontactfinderbackend.dto.responseDtoImpl;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RegistrationError extends Error{
    public RegistrationError(String message) {
        super(message);
    }


}
