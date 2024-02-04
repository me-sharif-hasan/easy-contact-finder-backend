package com.iishanto.easycontactfinderbackend.controller.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    @GetMapping
    public String get(){
        return "OKK";
    }
}
