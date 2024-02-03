package com.iishanto.easycontactfinderbackend.controller.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.AuthenticationErrorDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.AuthenticationSuccess;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.RegistrationError;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.RegistrationSuccess;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.RegistrationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import com.iishanto.easycontactfinderbackend.service.user.auth.google.GoogleAuthServices;
import com.iishanto.easycontactfinderbackend.service.user.UserService;
import com.iishanto.easycontactfinderbackend.service.user.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/auth")
public class AuthenticationController {
    private final GoogleAuthServices googleAuthServices;
    private final UserService userService;
    @PostMapping(path = "/with-google")
    public ResponseEntity <Object> loginWithGoogle(@RequestBody String requestBody){
        AuthenticationErrorDto authenticationErrorDto=new AuthenticationErrorDto();
        try{
            UserCredentialDto googleCredentialDto=new ObjectMapper().readValue(requestBody,UserCredentialDto.class);
            UserDto userDto=userService.loginWithGoogle(googleCredentialDto).getUserDto();
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        }catch (UserNotExistsException e){
            UserDto userDto=e.getUserDto();
            userService.registerWithGoogle(userDto);
            RegistrationSuccess registrationSuccess=new RegistrationSuccess("You have successfully registered!");
            registrationSuccess.setSkipLogin(true);
            return new ResponseEntity<>(registrationSuccess,HttpStatus.OK);
        } catch (LoginCredentialVerificationFailureException e){
            authenticationErrorDto.setMessage("Google authentication failed!");
            authenticationErrorDto.setException(e);
            return new ResponseEntity<>(authenticationErrorDto,HttpStatus.UNAUTHORIZED);
        } catch (Throwable e) {
            authenticationErrorDto.setMessage("Bad formatted json data");
            return new ResponseEntity<>(authenticationErrorDto,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "email-login")
    public ResponseEntity <Object> withEmail(@RequestBody UserCredentialDto loginCredentialDto){
        AuthenticationErrorDto authenticationErrorDto=new AuthenticationErrorDto();
        try{
            AuthenticationSuccess authenticationSuccess=userService.loginWithEmail(loginCredentialDto);
            System.out.println(authenticationSuccess);
            return new ResponseEntity<>(authenticationSuccess,HttpStatus.OK);
        }catch (LoginCredentialVerificationFailureException | UserNotExistsException e){
            authenticationErrorDto.setMessage(e.getMessage());
            return new ResponseEntity<>(authenticationErrorDto,HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping(path = "email-register")
    public ResponseEntity <Object> withEmail(@RequestBody UserRegistrationInfoDto userRegistrationInfoDto){
        RegistrationError registrationError=new RegistrationError();
        try{
            RegistrationSuccess registrationSuccess=userService.registerWithEmail(userRegistrationInfoDto);
            return new ResponseEntity<>(registrationSuccess,HttpStatus.OK);
        }catch (RegistrationFailureException e){
            registrationError.setException(e);
            registrationError.setMessage("Registration failed!");
            return new ResponseEntity<>(registrationError,HttpStatus.CONFLICT);
        }
    }
}
