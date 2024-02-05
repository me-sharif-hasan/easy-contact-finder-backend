package com.iishanto.easycontactfinderbackend.controller.app;

import com.iishanto.easycontactfinderbackend.dto.AuthStatusDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class TestController {
    @GetMapping
    public ResponseEntity<AuthStatusDto> get(){
        return new ResponseEntity<>(new AuthStatusDto("success"), HttpStatus.OK);
    }
}
