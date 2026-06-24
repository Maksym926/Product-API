package com.test.productapi.model;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@JsonPropertyOrder({"id", "name", "description", "brand", "category", "price", "productAvailable", "stockQuantity"})
@Document(collection = "products")
public class Product {
    @Id
    private String id;

    private String name;

    private String description;

    private String brand;

    private Long price;

    private String category;

    private Boolean productAvailable;

    private Long stockQuantity;

    public Product(String name, String description, String brand, String category, Long price, Boolean productAvailable, Long stockQuantity) {
        this.name = name;
        this.description = description;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.productAvailable = productAvailable;
        this.stockQuantity = stockQuantity;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setProductAvailable(Boolean productAvailable) {
        this.productAvailable = productAvailable;
    }

    public void setStockQuantity(Long stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getBrand() {
        return brand;
    }

    public Long getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public Boolean getProductAvailable() {
        return productAvailable;
    }

    public Long getStockQuantity() {
        return stockQuantity;
    }
}
