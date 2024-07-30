package org.example.service;


import org.example.dto.ExecutorResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ExecutorPaymentService {

    private final RestTemplate executorRestClient;

    public ExecutorPaymentService(RestTemplate executorRestClient) {
        this.executorRestClient = executorRestClient;
    }

    public ExecutorResponseDto executePaymentOk() {
        return executorRestClient.postForObject(
                "/payments/execute", null, ExecutorResponseDto.class
        );
    }

    public ResponseEntity<ExecutorResponseDto> executePaymentFailed(Long productId, Long clientId) {
        String url = "/payments/execute/400";
        try {
            return executorRestClient.exchange(
                    url,
                    HttpMethod.POST,
                    new HttpEntity<>(new ExecutorResponseDto(productId + "/" + clientId, "response"), new HttpHeaders()),
                    ExecutorResponseDto.class
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ExecutorResponseDto(
                            "Product with productId=" + productId + " AND clientId=" + clientId + " not found",
                            "NOT_FOUND")
                    , HttpStatus.NOT_FOUND
            );
        }
    }

    public ResponseEntity<ExecutorResponseDto> executePaymentZeroBalance(Long productId, Long clientId) {
        try {
            return executorRestClient.exchange(
                    "/payments/execute/500",
                    HttpMethod.POST,
                    new HttpEntity<>(new ExecutorResponseDto(productId + "/" + clientId, "response 500"), new HttpHeaders()),
                    ExecutorResponseDto.class
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new ExecutorResponseDto(
                            "productId=" + productId + " AND clientId=" + clientId + " - zero-balance. Not executed",
                            "NOT_EXECUTED")
                    , HttpStatus.PAYMENT_REQUIRED
            );
        }
    }
}
