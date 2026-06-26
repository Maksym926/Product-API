package com.test.core.repo;

import com.test.core.model.Product;

import java.util.List;
import java.util.Optional;


public interface ProductRepo {
    List<Product> findAllProducts(int size, int page);
    Optional<Product> findProductById(String id);
    boolean findProductByProductName(String name);
    void createProduct(Product product);
    long  deleteProduct(String productId);
    long updateProduct(Product product);
}
