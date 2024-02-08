package com.iishanto.easycontactfinderbackend.dto.responseDtoImpl;

import lombok.Data;

@Data
public class RegistrationSuccess extends Success{
    private Boolean skipLogin=false;
    private String token=null;
    public RegistrationSuccess(String message) {
        super(message);
    }

    public RegistrationSuccess() {
    }
}
