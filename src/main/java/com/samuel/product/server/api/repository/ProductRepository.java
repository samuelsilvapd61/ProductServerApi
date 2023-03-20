package com.samuel.product.server.api.repository;

import com.samuel.product.server.api.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    @Query("""
            {$and: [
            {$or: [{$where: '":#{#product.id}" == "null"'}, {'id': :#{#product.id}}]},
            {$or: [{$where: '":#{#product.name}" == "null"'}, {'name': :#{#product.name}}]},
            {$or: [{$where: '":#{#product.description}" == "null"'}, {'description': :#{#product.description}}]},
            {$or: [{$where: '":#{#product.category}" == "null"'}, {'category': :#{#product.category}}]},
            {$or: [{$where: '":#{#product.productBrand}" == "null"'}, {'productBrand': :#{#product.productBrand}}]},
            {$or: [{$where: '":#{#product.provider}" == "null"'}, {'provider': :#{#product.provider}}]},
            {$or: [{$where: '":#{#product.barCode}" == "null"'}, {'barCode': :#{#product.barCode}}]},
            {$or: [{$where: '":#{#product.fabricationDate}" == "null"'}, {'fabricationDate': :#{#product.fabricationDate}}]},
            {$or: [{$where: '":#{#product.expirationDate}" == "null"'}, {'expirationDate': :#{#product.expirationDate}}]},
            {$or: [{$where: '":#{#product.inclusionDate}" == "null"'}, {'inclusionDate': :#{#product.inclusionDate}}]}
            ]}""")
    List<Product> findProduct(Product product, Pageable pageable);
}
