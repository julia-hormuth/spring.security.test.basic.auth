package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<LoginDto> login(@RequestBody LoginDto loginDto) {
        if (loginDto.getUsername() == null || loginDto.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        }
        String token = loginService.login(loginDto.getUsername(), loginDto.getPassword());
        LoginDto output = new LoginDto();
        output.setToken(token);
        return ResponseEntity.ok(output);
    }

    @PostMapping("/private")
    public String login2() {
        return "Öffentliche API - Keine Authentifizierung erforderlich!";
    }
}
