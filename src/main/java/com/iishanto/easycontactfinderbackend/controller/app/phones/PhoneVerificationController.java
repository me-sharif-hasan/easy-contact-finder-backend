package com.iishanto.easycontactfinderbackend.controller.app.phones;

import com.iishanto.easycontactfinderbackend.dto.phoneVerification.PhoneVerificationCodeSendSuccessfulResponseDto;
import com.iishanto.easycontactfinderbackend.dto.phoneVerification.PhoneVerificationRequestReceiverDto;
import com.iishanto.easycontactfinderbackend.service.user.phone.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@RequestMapping("/api/phone")
public class PhoneVerificationController {
    PhoneService phoneService;
    @PostMapping(path = "send-verification-code")
    public ResponseEntity<Object> sendVerificationCode(@RequestBody PhoneVerificationRequestReceiverDto dto){
        //TODO: send verification code
        //Get the DTO
        //update phone entity verification log
        System.out.println(dto);
        PhoneVerificationCodeSendSuccessfulResponseDto phoneVerificationCodeSendSuccessfulResponseDto= phoneService.sendVerificationCode(dto);
        return new ResponseEntity<>(phoneVerificationCodeSendSuccessfulResponseDto, HttpStatus.OK);
    }
}
