package com.iishanto.easycontactfinderbackend.dto;

import lombok.Data;
import org.springframework.context.annotation.Primary;
@Data
public class LoginSuccessMessageDto {
    private String status="success";
    private String token;
    private UserDto user;
}
