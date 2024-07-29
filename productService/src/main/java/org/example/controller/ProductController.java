package org.example.controller;

import org.example.dto.ProductDto;
import org.example.dto.ProductResponseDto;
import org.example.exception.ProductException;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/add")
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) throws ProductException {
        return productService.getProductById(id);
    }

    @GetMapping("/client/{clientId}")
    public ProductResponseDto getProductsByClientId(@PathVariable Long clientId) throws ProductException {
        return productService.findAllByClientId(clientId);
    }

    @GetMapping("/{productId}/{clientId}")
    public ProductResponseDto getProductByClientId(@PathVariable Long productId, @PathVariable Long clientId) {
        return productService.findByProductIdAndClientId(productId, clientId);
    }

    @GetMapping("/all")
    public List<ProductDto> getAllProducts() throws ProductException {
        return productService.findAll();
    }
}