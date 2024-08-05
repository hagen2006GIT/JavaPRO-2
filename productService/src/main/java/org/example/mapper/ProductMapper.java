package org.example.mapper;

import org.example.dto.ProductDto;
import org.example.entity.Client;
import org.example.entity.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ProductMapper {
    default Product dtoToEntity(ProductDto productDto, Client client) {
        return new Product(productDto.getId(),
                productDto.getAccount(),
                productDto.getBalance(),
                productDto.getProductType(),
                client
        );
    }

    default ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(),
                product.getAccount(),
                product.getBalance(),
                product.getProductType(),
                product.getClientId().getId()
        );
    }

    default List<ProductDto> entityListToDtoList(List<Product> products) {
        List<ProductDto> list = new ArrayList<>();
        products.forEach(item -> list.add(entityToDto(item)));
        return list;
    }
}