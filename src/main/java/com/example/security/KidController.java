package com.example.security;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.model.Kid;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/kid")
public class KidController {

    @GetMapping
    public ResponseEntity<List<Kid>> getAll() {
        Kid kid = new Kid();
        kid.setName("John");
        List<Kid> kids = List.of(kid);
        return ResponseEntity.ok(kids);
    }
}
