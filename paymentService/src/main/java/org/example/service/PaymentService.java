package org.example.service;


import lombok.Setter;
import org.example.dto.ExecutorResponseDto;
import org.example.dto.ProductDto;
import org.example.dto.ProductResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Setter
public class PaymentService {

    private final RestTemplate restTemplate;
    private final ExecutorPaymentService executorPaymentService;

    @Value("${integrations.services.products-service.url}")
    private String productsServiceUrl;
    @Value("${integrations.clients.client1.id}")
    private Long clientId; //клиент для отладки приложения примеров выполнения

    public PaymentService(RestTemplate restTemplate, ExecutorPaymentService executorPaymentService) {
        this.executorPaymentService = executorPaymentService;
        this.restTemplate = new RestTemplateBuilder()
                .rootUri(productsServiceUrl)
                .build();
    }

    public ProductResponseDto getAllClientProducts() {
        return restTemplate.getForObject(
                productsServiceUrl + "/client/" + clientId, ProductResponseDto.class
        );
    }

    public ProductResponseDto getClientProductById(Long productId) {
        return restTemplate.getForObject(
                productsServiceUrl + "/" + productId + "/" + clientId, ProductResponseDto.class
        );
    }

    public ExecutorResponseDto checkPayment(Long productId) {
        ProductDto product = null;

        try {
            ProductResponseDto productDto = restTemplate.getForObject(
                    productsServiceUrl + "/" + productId + "/" + clientId, ProductResponseDto.class);
            assert productDto != null;
            product = productDto.getProductDtoList().get(0);
        } catch (Exception e) {
            ResponseEntity<ExecutorResponseDto> response = executorPaymentService.executePaymentFailed(productId,clientId);
            return response.getBody();
        }
        Double balance = product.getBalance();
        if (balance <= 0) {
            return executorPaymentService.executePaymentZeroBalance(productId, clientId).getBody();
        }
        return executorPaymentService.executePaymentOk();
    }
}
