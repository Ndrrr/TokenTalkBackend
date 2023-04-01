package com.tokentalk.emailproducer.controller;

import com.tokentalk.emailproducer.dto.request.SendEmailRequest;
import com.tokentalk.emailproducer.dto.response.SendEmailResponse;
import com.tokentalk.emailproducer.service.SendEmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class SendEmailController {

    private final SendEmailService sendEmailService;

    @PostMapping("/send")
    public SendEmailResponse sendEmail(@RequestBody @Valid SendEmailRequest request) {
        return sendEmailService.sendEmailService(request);
    }

}
