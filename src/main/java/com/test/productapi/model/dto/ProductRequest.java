package com.test.productapi.model.dto;



public record ProductRequest (

        String name,
        String description,
        String brand,
        Long price,
        String category,
        Boolean productAvailable,
        Long stockQuantity

){
}
