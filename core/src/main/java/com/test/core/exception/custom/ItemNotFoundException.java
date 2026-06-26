package com.test.core.exception.custom;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String id) {
        super("Product with id " + id + " not found");
    }
}
