package com.samuel.product.server.api.resource;

import com.samuel.product.server.api.domain.Product;
import com.samuel.product.server.api.domain.request.ProductRequest;
import com.samuel.product.server.api.service.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductServiceImpl productService;

    @PostMapping
    public ResponseEntity<Product> addProduct(@Valid @RequestBody ProductRequest request) {
        var produto = productService.addProduct(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        var lista = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @PatchMapping
    public ResponseEntity<Product> updateProduct(
            @RequestParam String id,
            @Valid @RequestBody ProductRequest request) {
        var produto = productService.updateProduct(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(produto);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestParam String id) {
        productService.deleteProduct(id);
    }
}
