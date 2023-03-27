package com.tokentalk.auth.service;

import com.tokentalk.auth.domain.User;
import com.tokentalk.auth.dto.request.LoginRequest;
import com.tokentalk.auth.dto.request.RegisterRequest;
import com.tokentalk.auth.dto.response.LoginResponse;
import com.tokentalk.auth.dto.response.ValidateTokenResponse;
import com.tokentalk.auth.error.ErrorCode;
import com.tokentalk.auth.error.errors.UserNotFoundException;
import com.tokentalk.auth.mapper.UserMapper;
import com.tokentalk.auth.repository.UserRepository;
import com.tokentalk.auth.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(RegisterRequest registerRequest) {
        userRepository.save(userMapper.toUser(registerRequest));
    }

    public LoginResponse login(LoginRequest loginRequest) {
        log.info("Start login: {}", loginRequest);
        User user = getUserByEmail(loginRequest.getEmail());
        validatePassword(loginRequest.getPassword(), user.getPassword());
        String token = jwtUtil.generateToken(user.getEmail());
        log.info("Token generated: {}", token);
        return LoginResponse.of(token, userMapper.toUserDto(user));
    }


    private void validatePassword(String password, String hashedPassword) {
        if(!passwordEncoder.matches(password, hashedPassword)) {
            throw exUserNotFound("Username or password is not correct");
        }
    }

    private UserNotFoundException exUserNotFound(String message) {
        return new UserNotFoundException(ErrorCode.USER_NOT_FOUND, message);
    }

    public ValidateTokenResponse validateToken(String token) {
        log.info("Start validating token: {}", token);
        String userEmail = jwtUtil.validateToken(token);
        log.info("User email: {}", userEmail);
        User user = getUserByEmail(userEmail);
        log.info("User id: {}", user.getId());
        return ValidateTokenResponse.of(user.getId());
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> exUserNotFound("User not found"));
    }

}
