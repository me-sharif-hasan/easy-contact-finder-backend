package com.iishanto.easycontactfinderbackend.service.user.auth;

import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import com.iishanto.easycontactfinderbackend.model.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    User verifyCredential(UserCredentialDto loginCredential) throws LoginCredentialVerificationFailureException, UserNotExistsException;
}
