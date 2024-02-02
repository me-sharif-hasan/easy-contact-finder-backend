package com.iishanto.easycontactfinderbackend.service.google.impl;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.testing.json.MockJsonFactory;
import com.iishanto.easycontactfinderbackend.dto.GoogleIdUserDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.GoogleCredentialDto;
import com.iishanto.easycontactfinderbackend.service.google.GoogleServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.util.Collections;

@Component
public class RestApiGoogleService implements GoogleServices {
    RestClient restClient=RestClient.create();
    @Value("${google.webclient.id}")
    private String WEB_CLIENT_ID;
    public GoogleIdUserDto verifyGoogleCredential(GoogleCredentialDto googleCredentialDto){
        RestClient restClient=RestClient.create();
        try {
            String verificationUrl = "https://www.googleapis.com/oauth2/v3/tokeninfo?id_token=" + googleCredentialDto.getToken();
            GoogleIdUserDto response=restClient.get().uri(verificationUrl).retrieve().body(GoogleIdUserDto.class);
            return response;
        } catch (Throwable e) {
            return null;
        }
    }
}
