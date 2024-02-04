package com.iishanto.easycontactfinderbackend.service.user.auth.email.impl;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.repository.UserRepository;
import com.iishanto.easycontactfinderbackend.service.user.auth.email.EmailAuthService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
@AllArgsConstructor
@Component
public class BasicEmailAuthServiceImpl implements EmailAuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    @Override
    public User verifyCredential(UserCredentialDto loginCredential) throws LoginCredentialVerificationFailureException {
        //check user email and password match
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginCredential.getEmail(),loginCredential.getPassword()));
        return userRepository.findByEmail(loginCredential.getEmail());
    }
}
