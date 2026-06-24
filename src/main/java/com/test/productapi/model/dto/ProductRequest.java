package com.test.productapi.model.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ProductRequest (
        @NotBlank(message = "Product name is required")
        @Size(max = 100, message = "Product name must be less than 100 characters")
        String name,
        @NotBlank(message = "Product description is required")
        String description,
        @NotBlank(message = "Product brand is required")
        @Size(max = 100, message = "Product brand must be less than 100 characters")
        String brand,


        @Min(value = 0, message = "Product price must be greater than or equal to 0")
        Long price,
        @NotBlank(message = "Product category is required")
        @Size(max = 100, message = "Product category must be less than 100 characters")
        String category,
        Boolean productAvailable,


        @Min(value = 0, message = "Stock quantity must be greater than 0")
        Long stockQuantity

){
}
