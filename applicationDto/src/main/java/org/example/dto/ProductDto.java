package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.example.model.ProductType;

@Getter
@AllArgsConstructor
@Setter
@Data
public class ProductDto {
    private Long id;
    private String account;
    private Double balance;
    private ProductType productType;
    private Long ClientId;
}