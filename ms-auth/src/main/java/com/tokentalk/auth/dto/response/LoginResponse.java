package com.tokentalk.auth.dto.response;

import com.tokentalk.auth.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String accessToken;
    private UserDto user;

    public static LoginResponse of(String token, UserDto user) {
        return new LoginResponse(token, user);
    }

}
