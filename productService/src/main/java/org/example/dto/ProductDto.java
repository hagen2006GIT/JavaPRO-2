package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.model.ProductType;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDto {
    private Long id;
    private String account;
    private Double balance;
    private ProductType productType;
    private Long userId;
}
