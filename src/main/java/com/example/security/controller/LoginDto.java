package com.example.security.controller;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
    private String token;
}
