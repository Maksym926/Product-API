package com.test.productapi.model.dto;

import com.test.productapi.model.Product;

public record ProductResponse(
        String id,
        String name,
        Long price,
        String category,
        boolean productAvailable,
        Long stockQuantity
) {
    public static ProductResponse from(Product product){
        return  new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCategory(),
                product.getProductAvailable(),
                product.getStockQuantity()
        );
    }
}
