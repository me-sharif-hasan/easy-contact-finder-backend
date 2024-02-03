package com.iishanto.easycontactfinderbackend.service.user.auth;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    UserDto verifyCredential(UserCredentialDto loginCredential) throws LoginCredentialVerificationFailureException, UserNotExistsException;
}
