package com.example.security.external;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    private static final String KEYCLOAK_TOKEN_URL = "http://localhost:8080/realms/oauth2-demo/protocol/openid-connect/token";
    private static final String CLIENT_ID = "spring-boot-client";
    private static final String CLIENT_SECRET = "cc3HA3hfV7E59bcm7hCUgPTNgCT6XJJF";
    private static final String GRANT_TYPE = "password";

    public String getToken(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("client_secret", CLIENT_SECRET);
        requestBody.add("grant_type", GRANT_TYPE);
        requestBody.add("username", username);
        requestBody.add("password", password);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(KEYCLOAK_TOKEN_URL, HttpMethod.POST, requestEntity, Map.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return (String) response.getBody().get("access_token");
            } else {
                throw new RuntimeException("Error: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            throw new RuntimeException("Error", e);
        }
    }
}
