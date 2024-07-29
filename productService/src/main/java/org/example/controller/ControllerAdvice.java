package org.example.controller;

import org.example.dto.ErrorResponseDto;
import org.example.exception.ClientException;
import org.example.exception.IntegrationException;
import org.example.exception.ProductException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(HttpClientErrorException.class)
    public ErrorResponseDto handleHttpClientErrorException(HttpClientErrorException e, WebRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(e.getStatusCode(),"Ошибка взаимодействия с продуктовым сервисом");
        return new ErrorResponseDto(e.getMessage(), e.getStatusCode().toString());
//        return handleExceptionInternal(e,problemDetail,new HttpHeaders(),e.getStatusCode(),request);
    }
    @ExceptionHandler(ProductException.class)
    public ErrorResponseDto handleProductException(ProductException e) {
        System.out.println("ProductException" + e.getMessage() + e.getHttpStatus().name());
        return new ErrorResponseDto(e.getMessage(), e.getHttpStatus().name());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ErrorResponseDto handleP1Exception(MethodArgumentTypeMismatchException e) {
        return new ErrorResponseDto(e.getMessage(), e.getErrorCode());
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