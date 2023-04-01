package com.tokentalk.email.handler.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SendEmailRequest {

    @NotBlank
    private final String destination;
    @NotBlank
    private final String subject;
    @NotBlank
    private final String body;

}
