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
//    private final ExecutorPaymentService executorPaymentService;


    public PaymentsController(PaymentService paymentService/*, ExecutorPaymentService executorPaymentService*/) {
        this.paymentService = paymentService;
//        this.executorPaymentService = executorPaymentService;
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
//        return executorPaymentService.executePaymentOk();
        return new ExecutorResponseDto("ID", "OK");
    }
}
