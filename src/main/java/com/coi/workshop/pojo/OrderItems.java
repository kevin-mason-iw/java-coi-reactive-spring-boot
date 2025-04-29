package com.coi.workshop.pojo;

public record OrderItems(String sku, Double unitPrice, int quantity, double total) {
}
