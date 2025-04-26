package com.javacoi.reactive_java_demo.model;

import java.time.LocalDateTime;

public record ManagementReport (String customerId, String email, LocalDateTime firstPurchase, LocalDateTime lastPurchase, Integer totalOrders, Double totalSales){

}
