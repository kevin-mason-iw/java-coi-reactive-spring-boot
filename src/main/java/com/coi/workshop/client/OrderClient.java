package com.coi.workshop.client;

import com.coi.workshop.repository.OrderRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.coi.workshop.exceptions.OrderNotFoundException;
import com.coi.workshop.model.Order;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderClient {

    private final OrderRepository orderRepository;

    public OrderClient(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    public Order getOrder(String orderNumber) {
        Optional<Order> orderOptional = orderRepository.getAllOrders().stream()
            .filter(order -> order.OrderNumber().equals(orderNumber))
            .findFirst();

        if (orderOptional.isEmpty()) {
            throw new OrderNotFoundException("Order Not Found");
        } else {
            return orderOptional.get();
        }
    }
}
