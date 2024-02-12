package com.iishanto.easycontactfinderbackend.service.user.recognition;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.iishanto.easycontactfinderbackend.dto.image.Base64ImageDto;
import com.iishanto.easycontactfinderbackend.dto.recognition.openCv.FaceRecognitionDataDto;
import com.iishanto.easycontactfinderbackend.dto.user.personal.UserDto;
import com.iishanto.easycontactfinderbackend.exception.FileSavingFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class RecognitionService {
    public abstract void saveImage(FaceRecognitionDataDto base64Image) throws Exception;
    public abstract List<UserDto> predict(Base64ImageDto base64Image) throws JsonProcessingException;
}
