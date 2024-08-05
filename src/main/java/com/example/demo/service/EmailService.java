package com.example.demo.service;


public interface EmailService {

    boolean send(String to, String email, String subject);

    String buildEmail(String name, String link, String password, boolean isValidEmail);

}
