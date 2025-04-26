package com.javacoi.reactive_java_demo.exceptions;

public class InventoryNotFoundException extends RuntimeException {
    public InventoryNotFoundException(String message) {
        super(message);
    }
}
