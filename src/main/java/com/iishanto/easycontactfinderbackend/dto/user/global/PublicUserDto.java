package com.iishanto.easycontactfinderbackend.dto.user.global;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.iishanto.easycontactfinderbackend.dto.phone.global.PublicPhoneDto;
import com.iishanto.easycontactfinderbackend.model.Address;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublicUserDto {
    private Long id;
    private String name;
    private PublicPhoneDto phones;
    private String picture;
}
