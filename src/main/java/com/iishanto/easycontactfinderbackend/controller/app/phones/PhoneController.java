package com.iishanto.easycontactfinderbackend.controller.app.phones;

import com.iishanto.easycontactfinderbackend.dto.phoneVerification.PhoneVerificationCodeSendSuccessfulResponseDto;
import com.iishanto.easycontactfinderbackend.dto.phoneVerification.PhoneVerificationRequestReceiverDto;
import com.iishanto.easycontactfinderbackend.dto.request.ContactAliasCollectionDto;
import com.iishanto.easycontactfinderbackend.dto.request.ContactAliasDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.NormalSuccessResponseDto;
import com.iishanto.easycontactfinderbackend.dto.server.ResponseDto;
import com.iishanto.easycontactfinderbackend.dto.server.error.ServerErrorResponseDto;
import com.iishanto.easycontactfinderbackend.dto.server.success.ServerSuccessResponseDto;
import com.iishanto.easycontactfinderbackend.model.PhoneAlias;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.service.phone.PhoneAliasService;
import com.iishanto.easycontactfinderbackend.service.phone.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/phone")
public class PhoneController {
    PhoneService phoneService;
    PhoneAliasService phoneAliasService;



    @PostMapping(path = "save-alias")
    public ResponseEntity<ResponseDto> saveContact(@RequestBody ContactAliasCollectionDto contactAliasCollectionDto){
        System.out.printf(contactAliasCollectionDto.toString());
        try{
            if (contactAliasCollectionDto.getAliases().isEmpty()) throw new Exception("No contact exists");
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            int total=phoneAliasService.savePhoneAliases(contactAliasCollectionDto,user).size();
            ResponseDto responseDto=new ServerSuccessResponseDto();
            responseDto.setMessage(total+" contacts saved successfully");
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            ResponseDto responseDto=new ServerErrorResponseDto();
            responseDto.setMessage("Error saving your contact.");
            return new ResponseEntity<>(responseDto,HttpStatus.OK);
        }
    }




    @PostMapping(path = "send-verification-code")
    public ResponseEntity<Object> sendVerificationCode(@RequestBody PhoneVerificationRequestReceiverDto dto){
        System.out.println(dto);
        PhoneVerificationCodeSendSuccessfulResponseDto phoneVerificationCodeSendSuccessfulResponseDto= phoneService.sendVerificationCode(dto);
        return new ResponseEntity<>(phoneVerificationCodeSendSuccessfulResponseDto, HttpStatus.OK);
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
