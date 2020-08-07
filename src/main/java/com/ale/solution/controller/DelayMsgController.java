package com.ale.solution.controller;

import com.ale.solution.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
  *
  * @author alewu
  * @date 2020/8/7
  */
@RestController
@RequestMapping("/register")
public class DelayMsgController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/sendCaptcha")
    public void sendCaptcha(String phoneNumber) {
        registerService.sendCaptcha(phoneNumber);
    }

    @PostMapping("/")
    public void register(String phoneNumber, String captcha) {
        registerService.register(phoneNumber, captcha);
    }

}
