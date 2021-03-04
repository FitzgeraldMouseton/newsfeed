package com.example.newsfeed.models;

import java.util.Map;

public class ValidationResponse {

    private final Map<String, String> errors;

    public ValidationResponse(Map<String, String> errors) {
        this.errors = errors;
    }
}
