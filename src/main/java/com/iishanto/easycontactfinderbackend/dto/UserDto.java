package com.iishanto.easycontactfinderbackend.dto;

import com.iishanto.easycontactfinderbackend.model.Phone;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
public class UserDto {
    private String name;
    private String email;
    private String picture;
    private Set<PhoneDto> phones;
    private String country;
    private Boolean usedSocialLogin=false;
}
