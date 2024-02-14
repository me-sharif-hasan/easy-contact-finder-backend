package com.iishanto.easycontactfinderbackend.controller.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iishanto.easycontactfinderbackend.dto.AuthStatusDto;
import com.iishanto.easycontactfinderbackend.dto.LoginSuccessMessageDto;
import com.iishanto.easycontactfinderbackend.dto.user.personal.UserDto;
import com.iishanto.easycontactfinderbackend.model.User;
import com.iishanto.easycontactfinderbackend.service.user.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api")
public class TestController {
    ModelMapper modelMapper;
    UserService userService;
    @GetMapping
    public ResponseEntity<LoginSuccessMessageDto> get(){
        LoginSuccessMessageDto loginSuccessMessageDto=new LoginSuccessMessageDto();
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDto userDto=modelMapper.map(user,UserDto.class);
        loginSuccessMessageDto.setData(userDto);
        loginSuccessMessageDto.setToken(userService.getToken(user));
        return new ResponseEntity<>(loginSuccessMessageDto, HttpStatus.OK);
    }
}
