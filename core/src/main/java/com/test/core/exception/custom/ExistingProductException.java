package com.test.core.exception.custom;

public class ExistingProductException extends RuntimeException {
    public ExistingProductException(String name) {
        super("The product with name " + name + " already exists");
    }
}
