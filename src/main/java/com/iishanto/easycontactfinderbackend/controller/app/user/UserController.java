package com.iishanto.easycontactfinderbackend.controller.app.user;

import com.iishanto.easycontactfinderbackend.dto.ResponseDto;
import com.iishanto.easycontactfinderbackend.dto.request.UserVerificationCodeRequestDto;
import com.iishanto.easycontactfinderbackend.exception.UserNotExistsException;
import com.iishanto.easycontactfinderbackend.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
@AllArgsConstructor
public class UserController {
    UserService userService;
    @PostMapping("verify")
    public ResponseEntity<ResponseDto> verify(@RequestBody UserVerificationCodeRequestDto userVerificationCodeRequestDto){
        try{
            userService.verify(userVerificationCodeRequestDto);
            return null;
        }catch (UserNotExistsException e){
            return null;
        }
    }
}
