package com.test.core.service;

import com.test.core.model.Product;
import com.test.core.model.dto.PageResponse;
import com.test.core.model.dto.ProductRequest;
import com.test.core.model.dto.ProductResponse;

public interface ProductServiceI {
    PageResponse<ProductResponse> getProducts(int size, int page);
    Product getProductById(String productId);
    ProductResponse createProduct(ProductRequest productRequest);
    ProductResponse updateProductPartially(String productId, ProductRequest updates);
    ProductResponse updateProductEntirely(String productId, ProductRequest productRequest);
    void deleteProduct(String productId);
}
