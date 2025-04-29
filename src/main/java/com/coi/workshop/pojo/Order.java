package com.coi.workshop.pojo;

import java.time.LocalDateTime;
import java.util.List;

public record Order(String OrderNumber, String customerId, List<OrderItems> orderItems, LocalDateTime orderDate, double totalAmount) {
}
