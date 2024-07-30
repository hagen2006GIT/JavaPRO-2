package org.example.dto;

import lombok.Getter;

@Getter
public class ErrorResponseDto {
    private final String message;
    private final String code;

    public ErrorResponseDto(String errorMessage, String errorCode) {
        this.message = errorMessage;
        this.code = errorCode;
    }
}