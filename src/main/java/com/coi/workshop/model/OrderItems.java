package com.coi.workshop.model;

public record OrderItems(String sku, Double unitPrice, int quantity, double total) {
}
