package com.coi.workshop.model.report;

public record InventoryReport(
    String sku,
    String productName,
    Integer sold,
    Integer stock) {
}
