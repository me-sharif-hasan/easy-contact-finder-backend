package com.iishanto.easycontactfinderbackend.service.google;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.testing.json.MockJsonFactory;
import com.iishanto.easycontactfinderbackend.dto.GoogleIdUserDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.GoogleCredentialDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Collections;

@Service
public interface GoogleServices {
    GoogleIdUserDto verifyGoogleCredential(GoogleCredentialDto googleCredentialDto);
}
