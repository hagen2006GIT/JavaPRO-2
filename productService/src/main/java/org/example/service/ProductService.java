package org.example.service;

import lombok.Setter;
import org.example.dto.ProductDto;
import org.example.dto.ProductResponseDto;
import org.example.exception.ProductException;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Setter
public class ProductService {
    @Autowired
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> findAll() throws ProductException {
        List<ProductDto> products = new ArrayList<>();
        for (Product it : productRepository.findAll()) {
            products.add(new ProductDto(it.getId(), it.getAccount(), it.getBalance(), it.getProductType(), it.getUserId()));
        }
        return products;
    }

    public ProductDto getProductById(Long id) throws ProductException {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductException("Product with id: " + id + " not found", HttpStatus.NOT_FOUND)
                );
        return new ProductDto(product.getId(), product.getAccount(), product.getBalance(), product.getProductType(), product.getUserId());
    }

    public ProductResponseDto findByProductIdAndClientId(Long productId, Long clientId) throws ProductException {
        try {
            Product product = productRepository.findByIdAndUserId(productId, clientId);
            if (product == null) {
                return new ProductResponseDto("Product with productId: " + productId + " AND clientId " + clientId + " not found");
            }
            ProductDto result = new ProductDto(product.getId(), product.getAccount(), product.getBalance(), product.getProductType(), product.getUserId());
            List<ProductDto> it = new ArrayList<>();
            it.add(result);
            return new ProductResponseDto(it);
        } catch (Exception e) {
            throw new ProductException("Product with productId: " + productId + " AND clientId " + clientId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    public ProductResponseDto findAllByClientId(Long id) {
        List<ProductDto> products = new ArrayList<>();
        for (Product it : productRepository.findAllByUserId(id)) {
            products.add(new ProductDto(it.getId(), it.getAccount(), it.getBalance(), it.getProductType(), it.getUserId()));
        }
        if (products.isEmpty()) {
            return new ProductResponseDto("Products by userId=" + id + " not found");
        }
        return new ProductResponseDto(products);
    }

    public ProductDto save(ProductDto productDto) {
        Product product = new Product();
        product.setAccount(productDto.getAccount());
        product.setBalance(productDto.getBalance());
        product.setProductType(productDto.getProductType());
        product.setUserId(productDto.getUserId());
        productRepository.save(product);
        productDto.setId(product.getId());
        return productDto;
    }
}
