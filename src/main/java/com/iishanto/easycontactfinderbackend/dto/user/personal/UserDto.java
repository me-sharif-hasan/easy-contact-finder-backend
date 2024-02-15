package com.iishanto.easycontactfinderbackend.dto.user.personal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.iishanto.easycontactfinderbackend.dto.phone.personal.PhoneDto;
import com.iishanto.easycontactfinderbackend.dto.UserVerificationDto;
import com.iishanto.easycontactfinderbackend.dto.image.Base64ImageDto;
import lombok.Data;

import java.util.Set;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String picture;
    private Base64ImageDto base64Image;
    private Boolean isPhotoVerified;
    private Set<PhoneDto> phones;
    private UserVerificationDto userVerification;
    private String country;
    private Boolean usedSocialLogin=false;
    private Float score;
}
