package com.samuel.product.server.api.domain.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank
    @Size(max = 100)
    private String name;
    @Size(max = 255)
    private String description;
    @NotBlank
    @Size(max = 100)
    private String category;
    @Size(max = 100)
    private String productBrand;
    @Size(max = 100)
    private String provider;
    @Min(0)
    @NotNull
    private Long quantity;
    @Size(max = 100)
    private String barCode;
    private String fabricationDate;
    private String expirationDate;

}
