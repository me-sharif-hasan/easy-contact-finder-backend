package com.iishanto.easycontactfinderbackend.service.user;

import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import com.iishanto.easycontactfinderbackend.dto.request.UserVerificationCodeRequestDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.AuthenticationSuccess;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.RegistrationSuccess;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.RegistrationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import com.iishanto.easycontactfinderbackend.exception.UserNotLoggedInException;
import com.iishanto.easycontactfinderbackend.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto registerWithGoogle(UserRegistrationInfoDto userRegistrationInfoDto,UserCredentialDto userCredentialDto) throws RegistrationFailureException;
    AuthenticationSuccess loginWithGoogle(UserCredentialDto userCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException;

    RegistrationSuccess registerWithEmail(UserRegistrationInfoDto userInformationDto) throws RegistrationFailureException;
    AuthenticationSuccess loginWithEmail(UserCredentialDto userCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException;

    User findUserByEmail(String email);
    User getCurrentUser() throws UserNotLoggedInException;
    String getToken(User user);

    Boolean verify(UserVerificationCodeRequestDto userVerificationCodeRequestDto) throws UserNotExistsException;

    void save(User user);
}
