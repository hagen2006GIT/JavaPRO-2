package org.example.service;

import lombok.Setter;
import org.example.dto.ExecutorResponseDto;
import org.example.dto.ProductDto;
import org.example.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
                "/client/" + clientId, ProductResponseDto.class
        );
    }

    public ProductResponseDto getClientProductById(Long productId) {
        ProductDto result = productRestClient.getForObject(
                "/" + productId/* + "/" + clientId*/, ProductDto.class
        );
        List<ProductDto> list = new ArrayList<>();
        list.add(result);
        return new ProductResponseDto(list);
    }

    public ExecutorResponseDto checkPayment(Long productId) {
        return productRestClient.getForObject(
                "/" + productId + "/" + clientId, /*ProductResponseDto.class*/ExecutorResponseDto.class);
    }
}
