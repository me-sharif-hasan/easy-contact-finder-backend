package com.iishanto.easycontactfinderbackend.dto.request;

import com.iishanto.easycontactfinderbackend.dto.phone.alias.PhoneAliasDto;
import com.iishanto.easycontactfinderbackend.dto.user.personal.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactAliasCollectionDto {
    private List<PhoneAliasDto> aliases;
    private UserDto contact;
}
