package com.iishanto.easycontactfinderbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iishanto.easycontactfinderbackend.model.PhoneAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContactAliasDto {
    private String name;
    private String phone;
    private PhoneAlias aliasTarget;
}
