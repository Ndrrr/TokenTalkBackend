package com.tokentalk.auth.controller;

import com.tokentalk.auth.dto.request.LoginRequest;
import com.tokentalk.auth.dto.request.RegisterRequest;
import com.tokentalk.auth.dto.request.ValidateTokenRequest;
import com.tokentalk.auth.dto.response.LoginResponse;
import com.tokentalk.auth.dto.response.ValidateTokenResponse;
import com.tokentalk.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest registerRequest) {
        authService.register(registerRequest);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/validate")
    public ValidateTokenResponse validateToken(@RequestBody @Valid ValidateTokenRequest request) {
        return authService.validateToken(request.getToken());
    }

}
