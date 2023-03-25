package com.tokentalk.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {

    private String token;
    private String fullName;

    public static LoginResponse of(String token, String fullName) {
        return new LoginResponse(token, fullName);
    }

}
