package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.service.LoginService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        if (loginDto.getUsername() == null || loginDto.getPassword() == null) {
            return "Falscher Benutzername oder Passwort!";
        }
        return loginService.login(loginDto.getUsername(), loginDto.getPassword());
    }

    @PostMapping("/private")
    public String login2() {
        return "Öffentliche API - Keine Authentifizierung erforderlich!";
    }
}
