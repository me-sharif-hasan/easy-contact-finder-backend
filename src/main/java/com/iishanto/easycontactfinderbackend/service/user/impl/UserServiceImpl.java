package com.iishanto.easycontactfinderbackend.service.user.impl;

import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import com.iishanto.easycontactfinderbackend.dto.request.UserVerificationCodeRequestDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.AuthenticationSuccess;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.RegistrationSuccess;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.RegistrationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.model.UserVerification;
import com.iishanto.easycontactfinderbackend.repository.UserRepository;
import com.iishanto.easycontactfinderbackend.service.user.auth.email.EmailAuthService;
import com.iishanto.easycontactfinderbackend.service.user.auth.google.GoogleAuthServices;
import com.iishanto.easycontactfinderbackend.service.user.UserService;
import com.iishanto.easycontactfinderbackend.service.user.registration.RegistrationService;
import com.iishanto.easycontactfinderbackend.service.user.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final GoogleAuthServices googleAuthServices;
    private final RegistrationService registrationService;
    private final EmailAuthService emailAuthService;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    @Override
    public UserDto registerWithGoogle(UserRegistrationInfoDto userRegistrationInfoDto,UserCredentialDto userCredentialDto) throws RegistrationFailureException {
        return registrationService.registerGoogle(userRegistrationInfoDto,userCredentialDto);
    }

    @Override
    public AuthenticationSuccess loginWithGoogle(UserCredentialDto userCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException {
        User user = googleAuthServices.verifyCredential(userCredentialDto);
        AuthenticationSuccess authenticationSuccess=new AuthenticationSuccess("Google authentication successful");
        authenticationSuccess.setUser(user);
        return authenticationSuccess;
    }

    @Override
    public RegistrationSuccess registerWithEmail(UserRegistrationInfoDto userInformationDto) throws RegistrationFailureException {
        UserDto userDto=registrationService.registerEmailUser(userInformationDto);
        return new RegistrationSuccess("You are successfully registered!");
    }

    @Override
    public AuthenticationSuccess loginWithEmail(UserCredentialDto userCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException {
        User user=emailAuthService.verifyCredential(userCredentialDto);
        AuthenticationSuccess authenticationSuccess=new AuthenticationSuccess("Login successful");
        authenticationSuccess.setUser(user);
        return authenticationSuccess;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public String getToken(User user) {
        return jwtService.generateToken(user);
    }


    public Boolean verify(UserVerificationCodeRequestDto userVerificationCodeRequestDto) throws UserNotExistsException {
        User user=findUserByEmail(userVerificationCodeRequestDto.getEmail());
        if(user==null) throw new UserNotExistsException("User with this specific email not exists");
        String code= userVerificationCodeRequestDto.getCode();
        UserVerification userVerification=new UserVerification();
        if(userVerification.getCode().equals(code)){
            userVerification.setState("verified");
            user.setUserVerification(userVerification);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
