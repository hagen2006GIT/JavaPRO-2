package org.example.controller;

import org.example.dto.ErrorResponseDto;
import org.example.exception.ClientException;
import org.example.exception.IntegrationException;
import org.example.exception.ProductException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductException.class)
    public ErrorResponseDto handleProductException(ProductException e) {
        return new ErrorResponseDto(e.getMessage(), e.getHttpStatus().name());
    }

    @ExceptionHandler(IntegrationException.class)
    public ErrorResponseDto handleP2Exception(IntegrationException e) {
        return new ErrorResponseDto(e.getMessage(), e.getHttpStatus().name());
    }

    @ExceptionHandler(ClientException.class)
    public ErrorResponseDto handleP3Exception(ClientException e) {
        return new ErrorResponseDto(e.getMessage(), e.getHttpStatus().name());
    }
}