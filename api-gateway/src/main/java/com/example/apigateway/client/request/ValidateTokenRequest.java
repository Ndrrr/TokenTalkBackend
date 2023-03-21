package com.example.apigateway.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class ValidateTokenRequest {

    private String token;

}
