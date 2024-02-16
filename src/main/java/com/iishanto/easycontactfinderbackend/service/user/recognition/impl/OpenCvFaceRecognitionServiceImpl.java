package com.iishanto.easycontactfinderbackend.service.user.recognition.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.api.client.json.Json;
import com.iishanto.easycontactfinderbackend.dto.image.Base64ImageDto;
import com.iishanto.easycontactfinderbackend.dto.image.FaceDetectDto;
import com.iishanto.easycontactfinderbackend.dto.recognition.openCv.FaceRecognitionDataDto;
import com.iishanto.easycontactfinderbackend.dto.user.personal.UserDto;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.service.user.recognition.RecognitionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class OpenCvFaceRecognitionServiceImpl extends RecognitionService {
    RestClient restClient;
    ModelMapper modelMapper;
    ObjectMapper objectMapper;
    @Autowired
    public OpenCvFaceRecognitionServiceImpl(@Value("${opencv.api.key}")String apiKey,@Value("${opencv.api.baseUrl}")String baseUrl,ModelMapper modelMapper,ObjectMapper objectMapper){
        restClient=RestClient.builder().defaultHeader("X-API-KEY",apiKey).baseUrl(baseUrl).build();
        this.modelMapper=modelMapper;
        System.out.println(apiKey+ " iishanto "+ baseUrl);
        this.objectMapper=objectMapper;
    }
    @Override
    public void saveImage(FaceRecognitionDataDto faceRecognitionDataDto) throws Exception {
//        System.out.println(faceRecognitionDataDto);
        String response = restClient.post().uri("/person").accept(MediaType.APPLICATION_JSON).body(faceRecognitionDataDto).retrieve().body(String.class);
        System.out.println("OPEN CV SAYS "+response);
    }

    public void updateImage(FaceRecognitionDataDto faceRecognitionDataDto) throws Exception{
        String response = restClient.patch().uri("/person").accept(MediaType.APPLICATION_JSON).body(faceRecognitionDataDto).retrieve().body(String.class);
        System.out.println("OPEN CV UPDATES: "+response);
    }

    @Override
    public List<UserDto> predict(Base64ImageDto base64Image) throws JsonProcessingException {
        try{
            FaceRecognitionDataDto faceRecognitionDataDto=new FaceRecognitionDataDto();
            System.out.println(faceRecognitionDataDto.getSearch_mode());
            faceRecognitionDataDto.setImages(Collections.singletonList(base64Image));
            String response=restClient.post().uri("/search").accept(MediaType.APPLICATION_JSON).body(faceRecognitionDataDto).retrieve().body(String.class);
            List <UserDto> userDtos=objectMapper.readValue(response, new TypeReference<List<UserDto>>() {});
            return userDtos;
        }catch (Exception e){
            throw e;
        }
    }
}
