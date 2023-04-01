package com.tokentalk.emailproducer.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokentalk.emailproducer.dto.request.SendEmailRequest;
import com.tokentalk.emailproducer.dto.response.SendEmailResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendEmailService {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public SendEmailResponse sendEmailService(SendEmailRequest request) {
        String id = UUID.randomUUID().toString();
        log.info("Sending email with id: {}", id);
        kafkaTemplate.send("email", id, mapToJson(request));
        return SendEmailResponse.of(id);
    }

    private String mapToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
