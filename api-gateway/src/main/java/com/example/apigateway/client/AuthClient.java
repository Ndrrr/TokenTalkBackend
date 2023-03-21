package com.example.apigateway.client;

import com.example.apigateway.client.request.ValidateTokenRequest;
import com.example.apigateway.client.response.ValidateTokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AuthClient {

    private final RestTemplate restTemplate;

    @Value("${paths.auth.url}")
    private String authUrl;

    public ResponseEntity<ValidateTokenResponse> validateToken(
            @RequestBody ValidateTokenRequest request) {
        return restTemplate.postForEntity(
                authUrl + "/validate", request, ValidateTokenResponse.class);
    }

}
