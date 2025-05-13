package com.vazcode.presentation.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.vazcode.application.usecases.email.SendEmailUseCase;

import java.io.IOException;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {
    private final SendEmailUseCase sendEmailUseCase;

    @PostMapping("/send-with-attachment")
    public ResponseEntity<String> sendEmailWithAttachment(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("text") String text,
            @RequestParam(value = "attachment", required = false) MultipartFile attachment) {
        try {
            if (attachment != null && !attachment.isEmpty()) {
                sendEmailUseCase.executeWithAttachment(
                    to, 
                    subject, 
                    text, 
                    attachment.getOriginalFilename(),
                    attachment.getInputStream()
                );
            } else {
                sendEmailUseCase.execute(to, subject, text);
            }
            return ResponseEntity.ok("Email enviado com sucesso");
        } catch (IOException e) {
            return ResponseEntity.badRequest().body("Erro ao processar anexo: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao enviar email: " + e.getMessage());
        }
    }
}