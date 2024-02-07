package com.iishanto.easycontactfinderbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class UserRegistrationInfoDto extends UserDto{
    private String password;
    private String confirmPassword;
}
