package com.samuel.product.server.api.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    private String name;
    private String description;
    private String category;
    private String productBrand;
    private String provider;
    private String barCode;
    private LocalDate fabricationDate;
    private LocalDate expirationDate;
    private LocalDateTime inclusionDate;

}
