package com.iishanto.easycontactfinderbackend.dto;

import com.iishanto.easycontactfinderbackend.dto.user.personal.UserDto;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class UserRegistrationInfoDto extends UserDto {
    private String password;
    private String confirmPassword;
}
