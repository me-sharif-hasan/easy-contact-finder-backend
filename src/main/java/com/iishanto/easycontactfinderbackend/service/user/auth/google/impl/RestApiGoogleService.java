package com.iishanto.easycontactfinderbackend.service.user.auth.google.impl;

import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.UserCredentialDto;
import com.iishanto.easycontactfinderbackend.dto.UserRegistrationInfoDto;
import com.iishanto.easycontactfinderbackend.exception.LoginCredentialVerificationFailureException;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import com.iishanto.easycontactfinderbackend.model.User;
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
    public User verifyCredential(UserCredentialDto googleCredentialDto) throws LoginCredentialVerificationFailureException, UserNotExistsException {
        RestClient restClient=RestClient.create();
        try {
            String verificationUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + googleCredentialDto.getToken();
            User response=restClient.get().uri(verificationUrl).retrieve().body(User.class);
            if(response==null) throw new LoginCredentialVerificationFailureException("Google authentication failed due to network error.");
            if (userRepository.findByEmail(response.getEmail())!=null){
                UserNotExistsException userNotExistsException=new UserNotExistsException("User not found");
                throw userNotExistsException;
            }else{
                return response;
            }
        } catch (Throwable e) {
            throw e;
        }
    }
}
