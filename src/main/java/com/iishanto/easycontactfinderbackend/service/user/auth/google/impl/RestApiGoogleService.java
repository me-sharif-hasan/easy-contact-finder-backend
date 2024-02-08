package com.iishanto.easycontactfinderbackend.service.user.auth.google.impl;

import com.iishanto.easycontactfinderbackend.dto.user.personal.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.repository.UserRepository;
import com.iishanto.easycontactfinderbackend.service.user.auth.google.GoogleAuthServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
@RequiredArgsConstructor
@Component
public class RestApiGoogleService implements GoogleAuthServices {
    RestClient restClient=RestClient.create();
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Value("${google.webclient.id}")
    private String WEB_CLIENT_ID;

    public User verifyCredential(UserCredentialDto googleCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException {
        RestClient restClient=RestClient.create();
        try {
            String verificationUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + googleCredentialDto.getToken();
            UserDto response=restClient.get().uri(verificationUrl).retrieve().body(UserDto.class);
            if(response==null) throw new LoginCredentialVerificationFailureException("Google authentication failed due to network error.");
            User user=userRepository.findByEmail(response.getEmail());
            if (user==null){
                UserNotExistsException userNotExistsException=new UserNotExistsException("User not found");
                userNotExistsException.setUserRegistrationInfoDto(modelMapper.map(response,UserRegistrationInfoDto.class));
                throw userNotExistsException;
            }else{
                System.out.println(user);
                return user;
            }
        } catch (Throwable e) {
            throw e;
        }
    }
}
