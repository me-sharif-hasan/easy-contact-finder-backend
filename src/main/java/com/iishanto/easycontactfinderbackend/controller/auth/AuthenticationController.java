package com.iishanto.easycontactfinderbackend.controller.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iishanto.easycontactfinderbackend.dto.LoginSuccessMessageDto;
import com.iishanto.easycontactfinderbackend.dto.user.personal.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.AuthenticationErrorDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.AuthenticationSuccess;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.RegistrationError;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.RegistrationSuccess;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.RegistrationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.service.user.auth.google.GoogleAuthServices;
import com.iishanto.easycontactfinderbackend.service.user.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/auth")
public class AuthenticationController {
    private final GoogleAuthServices googleAuthServices;
    private final UserService userService;
    private final ModelMapper modelMapper;


    private ResponseEntity<Object> googleLogin(UserCredentialDto googleCredentialDto,String requestBody) throws Exception {
        googleCredentialDto=new ObjectMapper().readValue(requestBody,UserCredentialDto.class);
        User user=userService.loginWithGoogle(googleCredentialDto).getUser();
        String token=userService.getToken(user);
        UserDto userDto=modelMapper.map(user,UserDto.class);
//        System.out.println("Number of phones: "+userDto.getPhones().size());
        LoginSuccessMessageDto loginSuccessMessageDto=new LoginSuccessMessageDto();
        loginSuccessMessageDto.setToken(token);
        loginSuccessMessageDto.setData(userDto);
        System.out.println("READY, not returning");
        return new ResponseEntity<>(loginSuccessMessageDto, HttpStatus.OK);
    }

    @PostMapping(path = "/with-google")
    public ResponseEntity <Object> loginWithGoogle(@RequestBody String requestBody) throws Exception {
        AuthenticationErrorDto authenticationErrorDto=new AuthenticationErrorDto();
        UserCredentialDto googleCredentialDto=null;
        System.out.println("Trying google login");
        try{
            googleCredentialDto=new ObjectMapper().readValue(requestBody,UserCredentialDto.class);
            return googleLogin(googleCredentialDto,requestBody);
//            googleCredentialDto=new ObjectMapper().readValue(requestBody,UserCredentialDto.class);
//            User user=userService.loginWithGoogle(googleCredentialDto).getUser();
//            String token=userService.getToken(user);
//            UserDto userDto=modelMapper.map(user,UserDto.class);
//            System.out.println("Number of phones: "+userDto.getPhones().size());
//            LoginSuccessMessageDto loginSuccessMessageDto=new LoginSuccessMessageDto();
//            loginSuccessMessageDto.setToken(token);
//            loginSuccessMessageDto.setData(userDto);
//            System.out.println("READY, not returning");
//            return new ResponseEntity<>(loginSuccessMessageDto, HttpStatus.OK);
        }catch (UserNotExistsException e){
            e.printStackTrace();
            if (googleCredentialDto==null) throw new LoginCredentialVerificationFailureException("Login failure, also can't get credential from google");
            UserRegistrationInfoDto regDto=e.getUserRegistrationInfoDto();
            System.out.println("Err: "+regDto);
            userService.registerWithGoogle(regDto,googleCredentialDto);
            RegistrationSuccess registrationSuccess=new RegistrationSuccess("You have successfully registered!");
            registrationSuccess.setSkipLogin(true);
            User user=userService.findUserByEmail(regDto.getEmail());
            if(user!=null){
                UserDto userDto=modelMapper.map(user,UserDto.class);
                registrationSuccess.setData(userDto);
            }
            //now do the login again
            return googleLogin(googleCredentialDto,requestBody);
//            return new ResponseEntity<>(registrationSuccess,HttpStatus.OK);
        } catch (Exception e){
            e.printStackTrace();
            authenticationErrorDto.setMessage("Google authentication failed!");
            authenticationErrorDto.setException(e);
            return new ResponseEntity<>(authenticationErrorDto,HttpStatus.UNAUTHORIZED);
        } catch (Throwable e) {
            e.printStackTrace();
            authenticationErrorDto.setMessage("Bad formatted json data");
            return new ResponseEntity<>(authenticationErrorDto,HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(path = "email-login")
    public ResponseEntity <Object> withEmail(@RequestBody UserCredentialDto loginCredentialDto){
        AuthenticationErrorDto authenticationErrorDto=new AuthenticationErrorDto();
        try{
            AuthenticationSuccess authenticationSuccess=userService.loginWithEmail(loginCredentialDto);
            String token=userService.getToken(authenticationSuccess.getUser());
            LoginSuccessMessageDto loginSuccessMessageDto=new LoginSuccessMessageDto();
            loginSuccessMessageDto.setData(modelMapper.map(authenticationSuccess.getUser(),UserDto.class));
            loginSuccessMessageDto.setToken(token);
            return new ResponseEntity<>(loginSuccessMessageDto,HttpStatus.OK);
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

    @GetMapping("test")
    public String test(){
        return "Success";
    }
}
