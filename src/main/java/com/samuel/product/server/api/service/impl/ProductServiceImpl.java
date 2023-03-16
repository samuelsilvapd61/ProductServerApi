package com.samuel.product.server.api.service.impl;

import com.samuel.product.server.api.domain.Product;
import com.samuel.product.server.api.repository.ProductRepository;
import com.samuel.product.server.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public Product addProduct(Product request) {
        return productRepository.save(request);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(String id, Product request) {
        var actualProduct = findByIdOrFail(id);
        var updateProduct = buildUpdatedProduct(actualProduct, request);

        return productRepository.save(updateProduct);
    }

    @Override
    public void deleteProduct(String id) {
        findByIdOrFail(id);
        productRepository.deleteById(id);
    }

    private Product findByIdOrFail(String id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
    }

    private Product buildUpdatedProduct(Product actualProduct, Product request) {
        return Product.builder()
                .id(actualProduct.getId())
                .name(request.getName() != null ? request.getName() : actualProduct.getName())
                .description(request.getDescription() != null ? request.getDescription() : actualProduct.getDescription())
                .provider(request.getProvider() != null ? request.getProvider() : actualProduct.getProvider())
                .fabricationDate(request.getFabricationDate() != null ? request.getFabricationDate() : actualProduct.getFabricationDate())
                .expirationDate(request.getExpirationDate() != null ? request.getExpirationDate() : actualProduct.getExpirationDate())
                .inclusionDate(actualProduct.getInclusionDate())
                .build();
    }
}
