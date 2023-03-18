package com.samuel.product.server.api.domain.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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
    private String barCode;
    private LocalDate fabricationDate;
    private LocalDate expirationDate;

}
