package com.test.productapi.repo;

import com.test.productapi.model.Product;
import com.test.productapi.model.dto.ProductResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ConditionalOnProperty(name = "app.database", havingValue = "mongodb") // creates the bean only if the property is mongodb
public class MongoDBRepo implements ProductRepo{
    @Override
    public Page<ProductResponse> findAllProducts(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<Product>findProductById(Long id) {
        return null;
    }

    @Override
    public void createProduct(Product product) {

    }

    @Override
    public void deleteProduct(Product product) {

    }
}
