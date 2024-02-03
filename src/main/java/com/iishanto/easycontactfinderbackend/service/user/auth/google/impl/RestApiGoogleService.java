package com.iishanto.easycontactfinderbackend.service.user.auth.google.impl;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import com.iishanto.easycontactfinderbackend.repository.UserRepository;
import com.iishanto.easycontactfinderbackend.service.user.auth.google.GoogleAuthServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class RestApiGoogleService implements GoogleAuthServices {
    RestClient restClient=RestClient.create();
    private final UserRepository userRepository;
    @Autowired
    public RestApiGoogleService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
    @Value("${google.webclient.id}")
    private String WEB_CLIENT_ID;
    public UserDto verifyCredential(UserCredentialDto googleCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException {
        RestClient restClient=RestClient.create();
        try {
            String verificationUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + googleCredentialDto.getToken();
            UserDto response=restClient.get().uri(verificationUrl).retrieve().body(UserDto.class);
            if (userRepository.findByEmail(response.getEmail())!=null){
                UserNotExistsException userNotExistsException=new UserNotExistsException();
                userNotExistsException.setUserDto(response);
                throw userNotExistsException;
            }else{
                return response;
            }
        } catch (Throwable e) {
            throw e;
        }
    }
}
