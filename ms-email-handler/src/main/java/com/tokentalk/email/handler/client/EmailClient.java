package com.tokentalk.email.handler.client;

import com.tokentalk.email.handler.dto.request.SendEmailRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailClient {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSource;

    public void sendEmail(SendEmailRequest request) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(emailSource);
            helper.setSubject(request.getSubject());
            helper.setTo(request.getDestination());
            helper.setText(request.getBody());
            javaMailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send email");
        }
    }

}
