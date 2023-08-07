package com.samuel.product.server.api.repository;

import com.samuel.product.server.api.domain.Product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE "
            + "(:#{#product.id} IS NULL OR p.id = :#{#product.id}) AND " +
            "(:#{#product.name} IS NULL OR p.name LIKE %:#{#product.name}%) AND " +
            "(:#{#product.description} IS NULL OR p.description LIKE %:#{#product.description}%) AND " +
            "(:#{#product.category} IS NULL OR p.category LIKE %:#{#product.category}%) AND " +
            "(:#{#product.productBrand} IS NULL OR p.productBrand LIKE %:#{#product.productBrand}%) AND " +
            "(:#{#product.provider} IS NULL OR p.provider LIKE %:#{#product.provider}%) AND " +
            "(:#{#product.quantity} IS NULL OR p.quantity = :#{#product.quantity}) AND " +
            "(:#{#product.barCode} IS NULL OR p.barCode LIKE %:#{#product.barCode}%) AND " +
            "(:#{#product.fabricationDate} IS NULL OR p.fabricationDate = :#{#product.fabricationDate}) AND " +
            "(:#{#product.expirationDate} IS NULL OR p.expirationDate = :#{#product.expirationDate}) AND " +
            "(:#{#product.inclusionDate} IS NULL OR DATE(p.inclusionDate) = DATE(:#{#product.inclusionDate}))")
    List<Product> findProduct(@Param("product") Product product, Pageable pageable);
}
