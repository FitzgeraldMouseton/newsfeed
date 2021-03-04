package com.example.newsfeed.models.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private final boolean result;

    public ResponseDto(boolean result) {
        this.result = result;
    }
}
