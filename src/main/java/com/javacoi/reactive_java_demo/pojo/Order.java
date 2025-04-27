package com.javacoi.reactive_java_demo.pojo;

import java.time.ZonedDateTime;
import java.util.List;

public record Order(String OrderNumber, String customerId, List<OrderItems> orderItems, ZonedDateTime orderDate, double totalAmount) {
}
