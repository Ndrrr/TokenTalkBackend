package com.tokentalk.email.handler.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tokentalk.email.handler.client.EmailClient;
import com.tokentalk.email.handler.dto.request.SendEmailRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EmailListener {

    private final EmailClient emailClient;
    private final ObjectMapper objectMapper;

    @KafkaListener(id = "emailListener", topics = "email")
    public void onEmailSend(ConsumerRecord<String, String> cr) {
        String key = cr.key();
        String requestString = cr.value();
        SendEmailRequest request = mapToRequest(requestString);
        log.info("Got Kafka message key:{}, value:{}", key, request);
        emailClient.sendEmail(request);
    }

    private SendEmailRequest mapToRequest(String json) {
        try {
            return objectMapper.readValue(json, SendEmailRequest.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
