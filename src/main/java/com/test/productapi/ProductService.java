package com.test.productapi;

import com.test.productapi.model.Product;
import com.test.productapi.model.dto.ProductRequest;
import com.test.productapi.model.dto.ProductResponse;
import com.test.productapi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Page<ProductResponse> getProducts(Pageable pageable) {
        return productRepo.findAllProducts(pageable);
    }

    public Product getProductById(Long productId) {
        return productRepo.findProductById(productId).orElseThrow();
    }

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = new Product(
                productRequest.name(),
                productRequest.description(),
                productRequest.brand(),
                productRequest.category(),
                productRequest.price(),
                productRequest.productAvailable(),
                productRequest.stockQuantity());
        productRepo.createProduct(product);
        return ProductResponse.from(product);
    }

    public ProductResponse updateProductPartially(Long productId, ProductRequest updates) {
        Product existingProduct = getProductById(productId);
        if(updates.name() != null) existingProduct.setProductName(updates.name());
        if(updates.description() != null) existingProduct.setProductDescription(updates.description());
        if(updates.brand() != null) existingProduct.setBrand(updates.brand());
        if(updates.category() != null) existingProduct.setCategory(updates.category());
        if(updates.productAvailable()!= null) existingProduct.setProductAvailable(updates.productAvailable());
        if(updates.stockQuantity() != null) existingProduct.setStockQuantity(updates.stockQuantity());

        productRepo.updateProduct(existingProduct);
        return ProductResponse.from(existingProduct);
    }
    public ProductResponse updateProductEntirely(Long productId, ProductRequest productRequest) {
        Product existingProduct = getProductById(productId);
        existingProduct.setProductName(productRequest.name());
        existingProduct.setProductDescription(productRequest.description());
        existingProduct.setBrand(productRequest.brand());
        existingProduct.setCategory(productRequest.category());
        existingProduct.setProductAvailable(productRequest.productAvailable());
        existingProduct.setStockQuantity(productRequest.stockQuantity());
        productRepo.updateProduct(existingProduct);
        return ProductResponse.from(existingProduct);
    }

    public void deleteProduct(Long productId) {
        Product product = getProductById(productId);
        productRepo.deleteProduct(product);
    }
}
