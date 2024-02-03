package com.iishanto.easycontactfinderbackend.service.user.impl;

import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.AuthenticationSuccess;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.RegistrationSuccess;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.RegistrationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.repository.UserRepository;
import com.iishanto.easycontactfinderbackend.service.user.auth.email.EmailAuthService;
import com.iishanto.easycontactfinderbackend.service.user.auth.google.GoogleAuthServices;
import com.iishanto.easycontactfinderbackend.service.user.UserService;
import com.iishanto.easycontactfinderbackend.service.user.registration.RegistrationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final GoogleAuthServices googleAuthServices;
    private final RegistrationService registrationService;
    private final EmailAuthService emailAuthService;
    private final UserRepository userRepository;

    @Override
    public UserDto registerWithGoogle(UserDto userDto) throws RegistrationFailureException {
        return registrationService.registerGoogle(userDto);
    }

    @Override
    public AuthenticationSuccess loginWithGoogle(UserCredentialDto userCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException {
        UserDto userDto = googleAuthServices.verifyCredential(userCredentialDto);
        AuthenticationSuccess authenticationSuccess=new AuthenticationSuccess("Google authentication successful");
        authenticationSuccess.setUserDto(userDto);
        return authenticationSuccess;
    }

    @Override
    public RegistrationSuccess registerWithEmail(UserRegistrationInfoDto userInformationDto) throws RegistrationFailureException {
        UserDto userDto=registrationService.registerEmailUser(userInformationDto);
        RegistrationSuccess registrationSuccess=new RegistrationSuccess("You are successfully registered!");
        return registrationSuccess;
    }

    @Override
    public AuthenticationSuccess loginWithEmail(UserCredentialDto userCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException {
        UserDto userDto=emailAuthService.verifyCredential(userCredentialDto);
        AuthenticationSuccess authenticationSuccess=new AuthenticationSuccess("Login successful");
        authenticationSuccess.setUserDto(userDto);
        return authenticationSuccess;
    }

    @Override
    public Boolean findUserByEmail(String email) {
        return userRepository.findByEmail(email)!=null;
    }
}
