package com.samuel.product.server.api.resource;

import com.samuel.product.server.api.domain.Product;
import com.samuel.product.server.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        var produto = productService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        var lista = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }
}
