package com.iishanto.easycontactfinderbackend.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iishanto.easycontactfinderbackend.dto.GoogleIdUserDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.AuthenticationErrorDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.GoogleCredentialDto;
import com.iishanto.easycontactfinderbackend.service.google.GoogleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {
    private GoogleServices googleServices;
    @Autowired
    public AuthenticationController(GoogleServices googleServices){
        this.googleServices=googleServices;
    }
    @PostMapping(path = "/with-google")
    public ResponseEntity <Object> loginWithGoogle(@RequestBody String requestBody){
        try{
            GoogleCredentialDto googleCredentialDto=new ObjectMapper().readValue(requestBody,GoogleCredentialDto.class);
            GoogleIdUserDto googleIdUserDto;
            if((googleIdUserDto=googleServices.verifyGoogleCredential(googleCredentialDto))!=null){
                System.out.println(googleIdUserDto);
                return new ResponseEntity<>(googleCredentialDto, HttpStatus.OK);
            }else{
                throw new Exception("Failure authenticating with Google");
            }
        }catch (Throwable e){
            AuthenticationErrorDto authenticationErrorDto=new AuthenticationErrorDto();
            authenticationErrorDto.setMessage("Google authentication failed! "+e.getMessage());
            return new ResponseEntity<>(authenticationErrorDto,HttpStatus.UNAUTHORIZED);
        }
    }
}
