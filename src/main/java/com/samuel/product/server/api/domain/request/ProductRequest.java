package com.samuel.product.server.api.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Data
public class ProductRequest {

    @NotBlank
    private String name;
    private String description;
    @NotBlank
    private String category;
    private String productBrand;
    private String provider;
    @Min(0)
    @NotNull
    private Long quantity;
    private String barCode;
    private LocalDate fabricationDate;
    private LocalDate expirationDate;

}
