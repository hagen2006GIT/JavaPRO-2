package org.example.controller;

import org.example.dto.ExecutorResponseDto;
import org.example.dto.ProductResponseDto;
import org.example.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentsController {

    private final PaymentService paymentService;

    public PaymentsController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/getAllClientProducts")
    public ProductResponseDto getAllClientProducts() {
        return paymentService.getAllClientProducts();
    }

    @GetMapping("/productId/{productId}")
    public ProductResponseDto getClientProductById(@PathVariable Long productId) {
        return paymentService.getClientProductById(productId);
    }

    @GetMapping("/execute/{productId}")
    public ExecutorResponseDto executePayment(@PathVariable Long productId) {
        return paymentService.checkPayment(productId);
    }

    @GetMapping("/healthCheck")
    public ExecutorResponseDto healthCheckConnection() {
        return new ExecutorResponseDto("ID", "OK");
    }
}