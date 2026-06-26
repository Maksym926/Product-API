package com.test.core.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonPropertyOrder({"id", "name", "description", "brand", "category", "price", "productAvailable", "stockQuantity"})
@Document(collection = "products")
public class ProductDocument {
    @Id
    private String id;

    private String name;

    private String description;

    private String brand;

    private Long price;

    private String category;

    private Boolean productAvailable;

    private Long stockQuantity;

    public ProductDocument() {}

    public ProductDocument(String id, String name, String description, String brand, String category, Long price, Boolean productAvailable, Long stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.productAvailable = productAvailable;
        this.stockQuantity = stockQuantity;
    }

    public Product toProduct() {
        Product product = new Product( name, description, brand, category, price, productAvailable, stockQuantity);
        product.setId(id);
        return product;
    }
    public static ProductDocument fromProduct(Product product) {
        return new ProductDocument(product.getId(), product.getName(), product.getDescription(), product.getBrand(), product.getCategory(), product.getPrice(), product.getProductAvailable(), product.getStockQuantity());
    }


}
