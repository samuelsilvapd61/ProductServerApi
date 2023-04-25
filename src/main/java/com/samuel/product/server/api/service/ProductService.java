package com.samuel.product.server.api.service;

import com.samuel.product.server.api.domain.Product;
import com.samuel.product.server.api.domain.request.ProductRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public Product addProduct(ProductRequest request);
    public List<Product> getProductsByParameterPageable(Product product, Pageable pageable);
    public Product updateProduct(Long id, ProductRequest request);
    public void deleteProduct(Long id);


}
