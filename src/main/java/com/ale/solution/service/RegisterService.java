package com.ale.solution.service;

public interface RegisterService {

    void register(String phoneNumber, String captcha);

    void sendCaptcha(String phoneNumber);
}
