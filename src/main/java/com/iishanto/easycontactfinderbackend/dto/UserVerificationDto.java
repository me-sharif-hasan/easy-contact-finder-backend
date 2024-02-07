package com.iishanto.easycontactfinderbackend.dto;

import lombok.Data;

@Data
public class UserVerificationDto {
    private String code;
    private String state;
}
