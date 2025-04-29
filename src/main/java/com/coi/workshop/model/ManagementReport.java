package com.coi.workshop.model;

import java.time.LocalDateTime;

public record ManagementReport (String customerId, String email, LocalDateTime firstPurchase, LocalDateTime lastPurchase, Integer totalOrders, Double totalSales){

}
