package com.samuel.product.server.api.converter;

import com.samuel.product.server.api.domain.Product;
import com.samuel.product.server.api.domain.request.ProductRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class ProductConverter {
    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .productBrand(request.getProductBrand())
                .provider(request.getProvider())
                .quantity(request.getQuantity())
                .barCode(request.getBarCode())
                .fabricationDate(request.getFabricationDate())
                .expirationDate(request.getExpirationDate())
                .build();
    }

    public Product buildNewProduct(Long id,
                                   String name,
                                   String description,
                                   String category,
                                   String productBrand,
                                   String provider,
                                   Long quantity,
                                   String barCode,
                                   LocalDate fabricationDate,
                                   LocalDate expirationDate,
                                   LocalDateTime inclusionDate
    ) {
        return Product.builder()
                .id(id)
                .name(name)
                .description(description)
                .category(category)
                .productBrand(productBrand)
                .provider(provider)
                .quantity(quantity)
                .barCode(barCode)
                .fabricationDate(fabricationDate)
                .expirationDate(expirationDate)
                .inclusionDate(inclusionDate)
                .build();
    }
}
