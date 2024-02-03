package com.iishanto.easycontactfinderbackend.service.user.auth.email.impl;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.service.user.auth.email.EmailAuthService;
import org.springframework.stereotype.Component;

@Component
public class BasicEmailAuthServiceImpl implements EmailAuthService {
    @Override
    public UserDto verifyCredential(UserCredentialDto loginCredential) throws LoginCredentialVerificationFailureException {
        //check user email and password match
        return null;
    }
}
