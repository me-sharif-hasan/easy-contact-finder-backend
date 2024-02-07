package com.iishanto.easycontactfinderbackend.service.user.recognition;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.iishanto.easycontactfinderbackend.dto.recognition.openCv.FaceRecognitionDataDto;
import com.iishanto.easycontactfinderbackend.exception.FileSavingFailureException;
import org.springframework.stereotype.Service;

@Service
public abstract class RecognitionService {
    public abstract void saveImage(FaceRecognitionDataDto base64Image) throws Exception;
    public void predict(String base64Image){}
}
