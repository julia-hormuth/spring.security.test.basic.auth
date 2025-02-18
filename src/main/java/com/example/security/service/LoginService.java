package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.external.AuthenticationService;
import com.example.security.model.User;
import com.example.security.repository.UserRepository;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(String username, String password) {

        User user = userRepository.findByUsername(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return this.authenticationService.getToken(user.getUsername(), password);
        } else {
            return "Falscher Benutzername oder Passwort!";
        }
    }
}
