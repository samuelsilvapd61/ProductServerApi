package com.samuel.product.server.api.converter;

import com.samuel.product.server.api.domain.Product;
import com.samuel.product.server.api.domain.request.ProductRequest;
import com.samuel.product.server.api.exception.ApiError;
import com.samuel.product.server.api.exception.ApiException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Component
public class ProductConverter {
    public Product toEntity(ProductRequest request) {
        LocalDate fabricationDate;
        try {
            fabricationDate = LocalDate.parse(request.getFabricationDate());
        } catch (DateTimeParseException e) {
            throw new ApiException(ApiError.PRODUCT_FABRICATION_DATE_TYPE_MISMATCH);
        }
        LocalDate expirationDate;
        try {
            expirationDate = LocalDate.parse(request.getExpirationDate());
        } catch (DateTimeParseException e) {
            throw new ApiException(ApiError.PRODUCT_EXPIRATION_DATE_TYPE_MISMATCH);
        }

        return Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .category(request.getCategory())
                .productBrand(request.getProductBrand())
                .provider(request.getProvider())
                .quantity(request.getQuantity())
                .barCode(request.getBarCode())
                .fabricationDate(fabricationDate)
                .expirationDate(expirationDate)
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
