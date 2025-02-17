package com.example.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Service
public class CreateUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    void create() {
        User user = new User();
        user.setUsername("hormuth");
        user.setPassword(passwordEncoder.encode("12345"));
        user.setRoles(List.of("USER"));
        userRepository.save(user);
    }
}
