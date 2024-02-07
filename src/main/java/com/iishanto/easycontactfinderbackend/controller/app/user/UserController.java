package com.iishanto.easycontactfinderbackend.controller.app.user;

import com.iishanto.easycontactfinderbackend.dto.ResponseDto;
import com.iishanto.easycontactfinderbackend.dto.UserDto;
import com.iishanto.easycontactfinderbackend.dto.image.Base64ImageDto;
import com.iishanto.easycontactfinderbackend.dto.recognition.openCv.FaceRecognitionDataDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.Error;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.FileSavingErrorResponseDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.FileSavingSuccessDto;
import com.iishanto.easycontactfinderbackend.dto.responseDtoImpl.Success;
import com.iishanto.easycontactfinderbackend.exception.UserNotLoggedInException;
import com.iishanto.easycontactfinderbackend.model.Phone;
import com.iishanto.easycontactfinderbackend.model.PhoneVerification;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.service.user.UserService;
import com.iishanto.easycontactfinderbackend.service.user.recognition.RecognitionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user")
@AllArgsConstructor
public class UserController {
    UserService userService;
    RecognitionService recognitionService;
    @PostMapping("save-info")
    public ResponseEntity<ResponseDto> saveUserInfo(@RequestBody UserDto userDto){
        try{
            FaceRecognitionDataDto faceRecognitionDataDto = getFaceRecognitionDataDto(userDto);
            User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            //prevent photo verification without phone verification
            Boolean isPhoneVerificationAvailable=false;
            if(user.getPhones()==null|| user.getPhones().isEmpty()){
                isPhoneVerificationAvailable=false;
            }else{
                for (Phone phone:user.getPhones()){
                    if(phone.getPhoneVerification()!=null&&phone.getPhoneVerification().getStatus().equals("verified")){
                        isPhoneVerificationAvailable=true;
                        break;
                    }
                }
            }
            if(!isPhoneVerificationAvailable) throw new Exception("You must verify your phone number first");
            user.setIsPhotoVerified(true);
            recognitionService.saveImage(faceRecognitionDataDto);
            Success fileSavingSuccessDto=new FileSavingSuccessDto();
            fileSavingSuccessDto.setStatus("success");
            fileSavingSuccessDto.setMessage("Face data verified successfully");
            userService.save(user);
            System.out.println(fileSavingSuccessDto);
            return new ResponseEntity<>(fileSavingSuccessDto, HttpStatus.OK);
        }catch (Exception e){
            Error error=new FileSavingErrorResponseDto();
            error.setStatus("error");
            error.setMessage("File can not be sent to backend");
            System.out.println(e.getLocalizedMessage());
            return new ResponseEntity<>(error,HttpStatus.OK);
        }
    }

    private FaceRecognitionDataDto getFaceRecognitionDataDto(UserDto userDto) throws UserNotLoggedInException {
        Base64ImageDto picture= userDto.getBase64Image();
        User user=userService.getCurrentUser();
        FaceRecognitionDataDto faceRecognitionDataDto =new FaceRecognitionDataDto();
        faceRecognitionDataDto.setId(String.valueOf(user.getId()));
        faceRecognitionDataDto.setName(user.getName());
        List <Base64ImageDto> base64ImageDtoList=new ArrayList<>();
        base64ImageDtoList.add(picture);
        faceRecognitionDataDto.setImages(base64ImageDtoList);
        return faceRecognitionDataDto;
    }

}
