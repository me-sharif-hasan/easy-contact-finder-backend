package com.iishanto.easycontactfinderbackend.dto;

import com.iishanto.easycontactfinderbackend.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class PhoneDto {
    private Long id;
    private String countryCode;
    private String number;

    private UserDto owner;
}
