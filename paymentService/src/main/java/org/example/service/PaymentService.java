package org.example.service;

import lombok.Setter;
import org.example.dto.ExecutorResponseDto;
import org.example.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Setter
public class PaymentService {

    private final RestTemplate productRestClient;

    @Value("${integrations.clients.client1.id}")
    private Long clientId; //клиент для отладки приложения примеров выполнения

    public PaymentService(RestTemplate productRestClient) {
        this.productRestClient = productRestClient;
    }

    public ProductResponseDto getAllClientProducts() {
        return productRestClient.getForObject(
                "/client/" + clientId, ProductResponseDto.class);
    }

    public ProductResponseDto getClientProductById(Long productId) {
        return productRestClient.getForObject(
                "/" + productId, ProductResponseDto.class);
    }

    public ExecutorResponseDto checkPayment(Long productId) {
        return productRestClient.getForObject(
                "/" + productId + "/" + clientId, ExecutorResponseDto.class);
    }
}
