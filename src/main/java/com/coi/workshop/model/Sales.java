package com.coi.workshop.model;

public record Sales(
    String productId,
    String productName,
    Integer totalSales,
    Double totalRevenue) {
}
