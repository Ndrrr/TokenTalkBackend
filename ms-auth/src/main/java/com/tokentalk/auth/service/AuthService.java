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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        User user = getUserByEmail(loginRequest.getEmail());
        validatePassword(loginRequest.getPassword(), user.getPassword());
        String token = jwtUtil.generateToken(user.getEmail());
        return LoginResponse.of(token, user.getFirstName() + " " + user.getLastName());
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
        String userEmail = jwtUtil.validateToken(token);
        User user = getUserByEmail(userEmail);
        return ValidateTokenResponse.of(user.getId());
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> exUserNotFound("User not found"));
    }

}
