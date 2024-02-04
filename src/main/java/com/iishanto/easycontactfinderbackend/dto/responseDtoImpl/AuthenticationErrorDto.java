package com.iishanto.easycontactfinderbackend.dto.responseDtoImpl;

import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@NoArgsConstructor
@Jacksonized
public class AuthenticationErrorDto extends Error {
    public AuthenticationErrorDto(String error){
        setMessage(error);
    }
    @Override
    public void setMessage(Object message) {
        super.setMessage(message);
    }
}
