package com.iishanto.easycontactfinderbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserCredentialDto {
    private String email;
    private String token;
    private String password;
}
