package com.samuel.product.server.api.converter;

import com.samuel.product.server.api.domain.Product;
import com.samuel.product.server.api.domain.request.ProductRequest;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .productBrand(request.getProductBrand())
                .provider(request.getProvider())
                .barCode(request.getBarCode())
                .fabricationDate(request.getFabricationDate())
                .expirationDate(request.getExpirationDate())
                .build();
    }
}
