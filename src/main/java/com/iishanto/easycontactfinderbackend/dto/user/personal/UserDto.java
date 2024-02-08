package com.iishanto.easycontactfinderbackend.dto.user.personal;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.iishanto.easycontactfinderbackend.dto.PhoneDto;
import com.iishanto.easycontactfinderbackend.dto.UserVerificationDto;
import com.iishanto.easycontactfinderbackend.dto.image.Base64ImageDto;
import com.iishanto.easycontactfinderbackend.model.Phone;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private String name;
    private String email;
    private String picture;
    private Base64ImageDto base64Image;
    private Boolean isPhotoVerified;
    private Set<PhoneDto> phones;
    private UserVerificationDto userVerification;
    private String country;
    private Boolean usedSocialLogin=false;
}
