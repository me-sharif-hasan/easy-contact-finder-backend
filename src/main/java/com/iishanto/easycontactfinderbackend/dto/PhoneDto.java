package com.iishanto.easycontactfinderbackend.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iishanto.easycontactfinderbackend.model.PhoneVerification;
import com.iishanto.easycontactfinderbackend.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.ToString;

@Data
public class PhoneDto {
    private Long id;
    private String countryCode;
    private String number;
    @JsonIgnore
    private UserDto owner;
    private PhoneVerification phoneVerification;
}
