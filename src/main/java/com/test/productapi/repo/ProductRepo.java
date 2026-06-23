package com.test.productapi.repo;

import com.test.productapi.model.Product;
import com.test.productapi.model.dto.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductRepo {
    Page<ProductResponse> findAllProducts(Pageable pageable);
    Optional<Product> findProductById(Long id);
    void createProduct(Product product);
    void deleteProduct(Product product);
    void updateProduct(Product product);
}
