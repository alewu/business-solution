package com.ale.solution.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author alewu
 */
@Service
@Slf4j
public class RegisterServiceImpl implements RegisterService{

    @Override
    public void register(String phoneNumber, String captcha) {
        log.info(phoneNumber + " " + captcha);
    }

    @Override
    public void sendCaptcha(String phoneNumber) {

    }
}
