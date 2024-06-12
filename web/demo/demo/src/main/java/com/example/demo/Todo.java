package com.example.demo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record Todo(String id, String title, String description, Boolean done) {
    public Todo {
        if (title == null || title.isBlank() || title.length() < 3) {
            throw new IllegalArgumentException("É nessessário informar um título maior que 3 caracteres");
        }
    }
}
