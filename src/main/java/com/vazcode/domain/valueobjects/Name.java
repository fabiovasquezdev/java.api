package com.vazcode.domain.valueobjects;

public record Name(String name) {
    public Name {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome não pode ser vazio");
        }
    }
}