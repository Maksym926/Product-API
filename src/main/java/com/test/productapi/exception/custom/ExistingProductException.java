package com.test.productapi.exception.custom;

public class ExistingProductException extends RuntimeException {
    public ExistingProductException(String message) {
        super(message);
    }
}
