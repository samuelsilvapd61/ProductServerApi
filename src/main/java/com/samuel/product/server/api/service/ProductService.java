package com.samuel.product.server.api.service;

import com.samuel.product.server.api.domain.Product;

import java.util.List;

public interface ProductService {

    public Product addProduct(Product product);
    public List<Product> getAllProducts();
    public Product updateProduct(String id, Product product);
    public void deleteProduct(String id);


}
