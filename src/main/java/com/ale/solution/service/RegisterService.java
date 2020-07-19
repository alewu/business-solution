package com.ale.solution.service;

/**
 * The interface Register service.
 */
public interface RegisterService {

    /**
     * Register.
     *
     * @param phoneNumber the phone number
     * @param captcha     the captcha
     */
    void register(String phoneNumber, String captcha);

    /**
     * Send captcha.
     *
     * @param phoneNumber the phone number
     */
    void sendCaptcha(String phoneNumber);
}
