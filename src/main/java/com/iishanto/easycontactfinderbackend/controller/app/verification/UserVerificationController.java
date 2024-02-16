package com.iishanto.easycontactfinderbackend.controller.app.verification;

import com.iishanto.easycontactfinderbackend.dto.phoneVerification.PhoneVerificationCodeSendSuccessfulResponseDto;
import com.iishanto.easycontactfinderbackend.dto.phoneVerification.PhoneVerificationRequestReceiverDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.NormalSuccessResponseDto;
import com.iishanto.easycontactfinderbackend.dto.server.error.ServerErrorResponseDto;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.service.phone.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping("/api/verification")
public class UserVerificationController {
    PhoneService phoneService;
    /**
     * Phone verification related functionalities
     */
    @PostMapping(path = "send-verification-code")
    public ResponseEntity<Object> sendVerificationCode(@RequestBody PhoneVerificationRequestReceiverDto dto){
        try{
            System.out.println(dto);
            PhoneVerificationCodeSendSuccessfulResponseDto phoneVerificationCodeSendSuccessfulResponseDto= phoneService.sendVerificationCode(dto);
            return new ResponseEntity<>(phoneVerificationCodeSendSuccessfulResponseDto, HttpStatus.OK);
        }catch (Exception e){
            ServerErrorResponseDto serverErrorResponseDto=new ServerErrorResponseDto();
            serverErrorResponseDto.setMessage("Failure sending verification code. Do this number okay?");
            return new ResponseEntity<>(serverErrorResponseDto,HttpStatus.CONFLICT);
        }
    }
    @PostMapping(path = "verify")
    public ResponseEntity<Object> verify(@RequestBody PhoneVerificationRequestReceiverDto dto){
        PhoneVerificationCodeSendSuccessfulResponseDto phoneVerificationCodeSendSuccessfulResponseDto=new PhoneVerificationCodeSendSuccessfulResponseDto("success","Your account is successfully verified");
        if(!phoneService.verify(dto)){
            System.out.println("ERROR");
            phoneVerificationCodeSendSuccessfulResponseDto.setStatus("error");
            phoneVerificationCodeSendSuccessfulResponseDto.setMessage("Verification code mismatch");
        }
        return new ResponseEntity<>(phoneVerificationCodeSendSuccessfulResponseDto,HttpStatus.OK);
    }
    @PostMapping(path = "revert")
    public ResponseEntity<Object> revert(){
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        NormalSuccessResponseDto normalSuccessResponseDto=new NormalSuccessResponseDto();
        if(user==null){
            normalSuccessResponseDto.setStatus("error");
            normalSuccessResponseDto.setStatus("You are not logged in");
            return new ResponseEntity<>(normalSuccessResponseDto,HttpStatus.BAD_REQUEST);
        }else{
            user.setPhones(null);
            //TODO: Also clear user verification
//            user.setUserVerification(null);
            normalSuccessResponseDto.setStatus("success");
            return new ResponseEntity<>(normalSuccessResponseDto,HttpStatus.OK);
        }
    }
}
