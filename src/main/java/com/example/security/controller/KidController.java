package com.example.security.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.model.Kid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class KidController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Öffentliche API - Keine Authentifizierung erforderlich!";
    }

    @GetMapping("/private")
    public Map<String, Object> privateEndpoint(@AuthenticationPrincipal Jwt jwt) {
        return Map.of("message", "Geschützte API - Authentifizierung erforderlich!", "user", jwt.getClaims());
    }

    @GetMapping
    public ResponseEntity<List<Kid>> getAll() {
        Kid kid = new Kid();
        kid.setName("John");
        List<Kid> kids = List.of(kid);
        return ResponseEntity.ok(kids);
    }
}
