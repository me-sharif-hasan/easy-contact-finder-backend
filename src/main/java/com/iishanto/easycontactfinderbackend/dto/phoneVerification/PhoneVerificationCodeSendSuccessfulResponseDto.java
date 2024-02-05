package com.iishanto.easycontactfinderbackend.dto.phoneVerification;

import com.iishanto.easycontactfinderbackend.dto.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PhoneVerificationCodeSendSuccessfulResponseDto extends ResponseDto {
    private String status;
    private String message;
}
