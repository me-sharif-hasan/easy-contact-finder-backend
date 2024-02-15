package com.iishanto.easycontactfinderbackend.dto.phone.alias;

import com.iishanto.easycontactfinderbackend.dto.phone.global.PublicPhoneDto;
import com.iishanto.easycontactfinderbackend.dto.user.global.PublicUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneAliasDto {
    private Long id;
    private String number;
    private String name;
    private PublicUserDto person;
    private PublicPhoneDto aliasTarget;
    private PublicUserDto aliasOwner;
}
