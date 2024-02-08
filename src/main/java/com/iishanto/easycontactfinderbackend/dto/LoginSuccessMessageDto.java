package com.iishanto.easycontactfinderbackend.dto;

import com.iishanto.easycontactfinderbackend.dto.user.personal.UserDto;
import lombok.Data;

@Data
public class LoginSuccessMessageDto {
    private String status="success";
    private String token;
    private UserDto data;
}
