package com.tokentalk.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeletePostRequest {

    @NotBlank
    private String id;

    @NotBlank
    private String authorEmail;

}
