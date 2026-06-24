package com.test.productapi.repo;

import com.test.productapi.model.Product;
import com.test.productapi.model.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface ProductRepo {
    List<Product> findAllProducts(int size, int page);
    Optional<Product> findProductById(String id);
    void createProduct(Product product);
    void deleteProduct(String productId);
    void updateProduct(Product product);
}
