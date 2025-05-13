package com.vazcode.domain.valueobjects;

import java.util.regex.Pattern;

public record Email(String value) {
    private static final Pattern EMAIL_PATTERN = 
        Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public Email {
        if (!isValid(value)) {
            throw new IllegalArgumentException("Formato de email inválido");
        }
    }

    private static boolean isValid(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}