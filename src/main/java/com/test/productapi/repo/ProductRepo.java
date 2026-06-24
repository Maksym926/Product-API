package com.test.productapi.repo;

import com.test.productapi.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepo {
    List<Product> findAllProducts(int size, int page);
    Optional<Product> findProductById(String id);
    boolean findProductByProductName(String name);
    void createProduct(Product product);
    void deleteProduct(String productId);
    void updateProduct(Product product);
}
