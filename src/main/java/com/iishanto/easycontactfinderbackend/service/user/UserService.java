package com.iishanto.easycontactfinderbackend.service.user;

import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.AuthenticationSuccess;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.RegistrationSuccess;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.RegistrationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto registerWithGoogle(UserDto userDto) throws RegistrationFailureException;
    AuthenticationSuccess loginWithGoogle(UserCredentialDto userCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException;

    RegistrationSuccess registerWithEmail(UserRegistrationInfoDto userInformationDto) throws RegistrationFailureException;
    AuthenticationSuccess loginWithEmail(UserCredentialDto userCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException;

    Boolean findUserByEmail(String email);
}
