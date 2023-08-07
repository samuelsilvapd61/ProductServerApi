package com.samuel.product.server.api.resource;

import com.samuel.product.server.api.converter.ProductConverter;
import com.samuel.product.server.api.domain.Product;
import com.samuel.product.server.api.domain.request.ProductRequest;
import com.samuel.product.server.api.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductServiceImpl productService;
    private final ProductConverter productConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@Valid @RequestBody ProductRequest request) {
        productService.addProduct(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<Product>> getProductsByParameterPageable(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String productBrand,
            @RequestParam(required = false) String provider,
            @RequestParam(required = false) Long quantity,
            @RequestParam(required = false) String barCode,
            @RequestParam(required = false) LocalDate fabricationDate,
            @RequestParam(required = false) LocalDate expirationDate,
            @RequestParam(required = false) LocalDate inclusionDate,
            @PageableDefault Pageable pageable
    ) {
        LocalDateTime inclusionDateTime = null;
        if (inclusionDate != null) {
            inclusionDateTime = inclusionDate.atStartOfDay();
        }
        var product = productConverter.buildNewProduct(
                id, name, description, category, productBrand, provider, quantity,
                barCode, fabricationDate, expirationDate, inclusionDateTime);
        var lista = productService.getProductsByParameterPageable(product, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(
            @RequestParam(required = false, defaultValue = "") Long id,
            @RequestBody ProductRequest request
    ) {
        productService.updateProduct(id, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestParam(required = false, defaultValue = "") Long id) {
        productService.deleteProduct(id);
    }
}
