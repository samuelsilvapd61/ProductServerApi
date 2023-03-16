package com.samuel.product.server.api.service;

import com.samuel.product.server.api.domain.Product;
import com.samuel.product.server.api.domain.request.ProductRequest;

import java.util.List;

public interface ProductService {

    public Product addProduct(ProductRequest request);
    public List<Product> getAllProducts();
    public Product updateProduct(String id, ProductRequest request);
    public void deleteProduct(String id);


}
