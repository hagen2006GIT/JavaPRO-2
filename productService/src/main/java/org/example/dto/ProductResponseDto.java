package org.example.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class ProductResponseDto {
    private String status;
    private List<ProductDto> productDtoList;

    public ProductResponseDto(String status) {
        this.status = status;
        this.productDtoList = null;
    }

    public ProductResponseDto(List<ProductDto> products) {
        this.productDtoList = products;
        this.status = "success";
    }
}
