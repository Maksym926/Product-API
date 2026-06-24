package com.test.productapi;

import com.test.productapi.exception.custom.ExistingProductException;
import com.test.productapi.model.Product;
import com.test.productapi.model.dto.PageResponse;
import com.test.productapi.model.dto.ProductRequest;
import com.test.productapi.model.dto.ProductResponse;
import com.test.productapi.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;


    public PageResponse<ProductResponse> getProducts(int size, int page) {
        List<ProductResponse> products =  productRepo.findAllProducts(size, page)
                .stream()
                .map(ProductResponse::from)
                .toList();
        return new PageResponse<>(products, page, size);
    }

    public Product getProductById(String productId) {
        return productRepo.findProductById(productId).orElseThrow();
    }

    public ProductResponse createProduct(ProductRequest productRequest) {

        if(productRepo.findProductByProductName(productRequest.name())){

            throw new ExistingProductException("The product with name: " + productRequest.name() + " already exists");
        }

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

    public ProductResponse updateProductPartially(String productId, ProductRequest updates) {
        Product existingProduct = productRepo.findProductById(productId).orElseThrow();

        if (updates.name() != null)             existingProduct.setName(updates.name());
        if (updates.description() != null)      existingProduct.setDescription(updates.description());
        if (updates.brand() != null)            existingProduct.setBrand(updates.brand());
        if (updates.category() != null)         existingProduct.setCategory(updates.category());
        if (updates.price() != null)            existingProduct.setPrice(updates.price());
        if (updates.productAvailable() != null) existingProduct.setProductAvailable(updates.productAvailable());
        if (updates.stockQuantity() != null)    existingProduct.setStockQuantity(updates.stockQuantity());

        productRepo.updateProduct(existingProduct);
        return ProductResponse.from(existingProduct);
    }
    public ProductResponse updateProductEntirely(String productId, ProductRequest productRequest) {
        Product existingProduct = productRepo.findProductById(productId).orElseThrow();
        existingProduct.setName(productRequest.name());
        existingProduct.setDescription(productRequest.description());
        existingProduct.setBrand(productRequest.brand());
        existingProduct.setCategory(productRequest.category());
        existingProduct.setPrice(productRequest.price());
        existingProduct.setProductAvailable(productRequest.productAvailable());
        existingProduct.setStockQuantity(productRequest.stockQuantity());
        productRepo.updateProduct(existingProduct);
        return ProductResponse.from(existingProduct);
    }

    public void deleteProduct(String productId) {
        productRepo.deleteProduct(productId);
    }

}
