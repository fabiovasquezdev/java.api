package com.vazcode.application.usecases.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class SendEmailUseCase {
    private final JavaMailSender emailSender;

    public void execute(String to, String subject, String text) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Falha ao enviar email", e);
        }
    }

    public void executeWithAttachment(String to, String subject, String text, String fileName, InputStream attachment) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            helper.addAttachment(fileName, new InputStreamResource(attachment));
            emailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Falha ao enviar email com anexo", e);
        }
    }
}