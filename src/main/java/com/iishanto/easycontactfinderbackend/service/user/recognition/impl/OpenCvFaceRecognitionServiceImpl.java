package com.iishanto.easycontactfinderbackend.service.user.recognition.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.iishanto.easycontactfinderbackend.dto.recognition.openCv.FaceRecognitionDataDto;
import com.iishanto.easycontactfinderbackend.service.user.recognition.RecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;

@Component
public class OpenCvFaceRecognitionServiceImpl extends RecognitionService {
    RestClient restClient;
    @Autowired
    public OpenCvFaceRecognitionServiceImpl(@Value("${opencv.api.key}")String apiKey,@Value("${opencv.api.baseUrl}")String baseUrl){
        restClient=RestClient.builder().defaultHeader("X-API-KEY",apiKey).baseUrl(baseUrl).build();
        System.out.println(apiKey+ " iishanto "+ baseUrl);
    }
    @Override
    public void saveImage(FaceRecognitionDataDto faceRecognitionDataDto) throws Exception {
        System.out.println(faceRecognitionDataDto);
        String response = restClient.post().uri("/person").accept(MediaType.APPLICATION_JSON).body(faceRecognitionDataDto).retrieve().body(String.class);
        System.out.println("OPEN CV SAYS "+response);
    }
}
