package org.example.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
public class ProductResponseDto {
    private String status;
    private List<ProductDto> productDtoList;

    public ProductResponseDto(String status, List<ProductDto> productDtoList) {
        this.status = status;
        this.productDtoList = productDtoList;
    }

    public ProductResponseDto(String status) {
        this(status, new ArrayList<>());
    }

    public ProductResponseDto(List<ProductDto> productDtoList) {
        this("success", productDtoList);
    }
}