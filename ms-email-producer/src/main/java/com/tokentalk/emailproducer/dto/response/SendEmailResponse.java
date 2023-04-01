package com.tokentalk.emailproducer.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class SendEmailResponse {

    private final String id;

}
