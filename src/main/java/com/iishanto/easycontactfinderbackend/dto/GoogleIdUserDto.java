package com.iishanto.easycontactfinderbackend.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GoogleIdUserDto {
    private String name;
    private String email;
    private String picture;

}
