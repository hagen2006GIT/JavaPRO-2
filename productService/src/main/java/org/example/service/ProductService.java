package org.example.service;

import lombok.Setter;
import org.example.dto.ExecutorResponseDto;
import org.example.dto.ProductDto;
import org.example.dto.ProductResponseDto;
import org.example.exception.ProductException;
import org.example.mapper.ProductMapper;
import org.example.entity.Product;
import org.example.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class ProductService {
    private final ProductRepository productRepository;
    private final ExecutorPaymentService executorPaymentService;
    private final ClientService clientService;
    private final ProductMapper productMapper;


    public ProductService(ProductRepository productRepository,
                          ExecutorPaymentService executorPaymentService,
                          ClientService clientService,
                          ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.executorPaymentService = executorPaymentService;
        this.clientService = clientService;
        this.productMapper = productMapper;
    }

    public List<ProductDto> findAll() throws ProductException {
        return productMapper.entityListToDtoList(productRepository.findAll());
    }

    public ProductResponseDto getProductById(Long productId) throws ProductException {
        return productRepository.findById(productId).map(value -> new ProductResponseDto("success",
                        List.of(productMapper.entityToDto(value)
                        )
                )
        ).orElseGet(() ->
                new ProductResponseDto("Product with productId: " + productId + " not found")
        );
    }

    public ExecutorResponseDto executeByProductIdAndClientId(Long productId, Long clientId) throws ProductException {
        try {
            Product product = productRepository.findByIdAndClientId(productId, clientService.findByIdCl(clientId));
            if (product == null) {
                ResponseEntity<ExecutorResponseDto> response = executorPaymentService.executePaymentFailed(productId, clientId);
                return response.getBody();
            }
            Double balance = product.getBalance();
            if (balance <= 0) {
                return executorPaymentService.executePaymentZeroBalance(productId, clientId).getBody();
            }
            return executorPaymentService.executePaymentOk();
        } catch (Exception e) {
            throw new ProductException("Product with productId: " + productId + " AND clientId " + clientId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    public ProductDto findProductByProductIdAndClientId(Long productId, Long clientId) throws ProductException {
        try {
            Product product = productRepository.findByIdAndClientId(productId, clientService.findByIdCl(clientId));
            return productMapper.entityToDto(product);
        } catch (Exception e) {
            throw new ProductException("Product with productId: " + productId + " AND clientId " + clientId + " not found", HttpStatus.NOT_FOUND);
        }
    }

    public ProductResponseDto findAllByClientId(Long clientId) {
        List<ProductDto> products = productMapper.entityListToDtoList(productRepository.findAll());
        if (products.isEmpty()) {
            return new ProductResponseDto("Products by clientId=" + clientId + " not found");
        }
        return new ProductResponseDto(products);
    }

    public ProductDto save(ProductDto productDto) {
        return productMapper.entityToDto(
                productRepository.save(
                        productMapper.dtoToEntity(
                                productDto, clientService.findByIdCl(productDto.getClientId())
                        )
                )
        );
    }
}