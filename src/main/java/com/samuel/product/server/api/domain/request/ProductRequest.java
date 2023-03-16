package com.samuel.product.server.api.domain.request;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProductRequest {

    private String name;
    private String description;
    private String provider;
    private LocalDate fabricationDate;
    private LocalDate expirationDate;

}
