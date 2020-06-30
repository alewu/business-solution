package com.ale.solution.controller;

import com.ale.solution.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/sendCaptcha")
//    @PreventRepetitionAnnotation
    public void sendCaptcha(String phoneNumber) {
        registerService.sendCaptcha(phoneNumber);
    }

    @PostMapping("/")
//    @PreventRepetitionAnnotation
    public void register(String phoneNumber, String captcha) {
        registerService.register(phoneNumber, captcha);
    }

}
