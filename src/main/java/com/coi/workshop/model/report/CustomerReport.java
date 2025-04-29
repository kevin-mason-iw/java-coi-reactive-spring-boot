package com.coi.workshop.model.report;

import java.time.LocalDateTime;

public record CustomerReport(
    String customerId,
    String email,
    LocalDateTime firstPurchase,
    LocalDateTime lastPurchase,
    Integer totalOrders,
    Double totalSales) {

}
