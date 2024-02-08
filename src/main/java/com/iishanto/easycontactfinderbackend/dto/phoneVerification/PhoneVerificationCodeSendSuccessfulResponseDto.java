package com.iishanto.easycontactfinderbackend.dto.phoneVerification;

import com.iishanto.easycontactfinderbackend.dto.server.ResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PhoneVerificationCodeSendSuccessfulResponseDto extends ResponseDto {
    public PhoneVerificationCodeSendSuccessfulResponseDto(String status,String message){
        setStatus(status);
        setMessage(message);
    }

}
