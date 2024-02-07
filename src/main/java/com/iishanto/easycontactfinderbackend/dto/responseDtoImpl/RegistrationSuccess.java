package com.iishanto.easycontactfinderbackend.dto.responseDtoImpl;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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
