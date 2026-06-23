package com.test.productapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    private String productDescription;

    private String brand;

    private Long price;

    private String category;

    private Boolean productAvailable;

    private Long stockQuantity;

    public Product(String productName, String productDescription, String brand, String category, Long price, Boolean productAvailable, Long stockQuantity) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.brand = brand;
        this.category = category;
        this.price = price;
        this.productAvailable = productAvailable;
        this.stockQuantity = stockQuantity;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
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

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
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
