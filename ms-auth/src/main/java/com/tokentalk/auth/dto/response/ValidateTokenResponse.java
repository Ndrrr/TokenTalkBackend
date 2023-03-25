package com.tokentalk.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ValidateTokenResponse {

    private Long userId;

}
