package com.samuel.product.server.api.service.impl;

import com.samuel.product.server.api.converter.ProductConverter;
import com.samuel.product.server.api.domain.Product;
import com.samuel.product.server.api.domain.request.ProductRequest;
import com.samuel.product.server.api.repository.ProductRepository;
import com.samuel.product.server.api.service.ProductService;
import com.samuel.product.server.api.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductConverter productConverter;

    public Product addProduct(ProductRequest request) {
        var product = productConverter.toEntity(request);
        product.setInclusionDate(DateUtil.nowDateTime());
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product updateProduct(String id, ProductRequest request) {
        var actualProduct = findByIdOrFail(id);

        var productWithUpdates = productConverter.toEntity(request);
        var updatedProduct = buildUpdatedProduct(actualProduct, productWithUpdates);

        return productRepository.save(updatedProduct);
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
