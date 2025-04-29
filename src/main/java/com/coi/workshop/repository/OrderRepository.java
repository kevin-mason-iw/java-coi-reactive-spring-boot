package com.coi.workshop.repository;

import com.coi.workshop.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Repository
public class OrderRepository {

    private final ObjectMapper objectMapper;

    public OrderRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Order> getAllOrders() {
        String jsonFile = "src/main/resources/data/orders.json";
        List<Order> orders;
        try {
            orders = loadOrderData(jsonFile);
        } catch (IOException e) {
            orders = Collections.emptyList();
        }
        return orders;
    }


    public List<Order> loadOrderData(String jsonFile) throws IOException {
        return objectMapper.readValue(new File(jsonFile), new TypeReference<>() {
        });
    }
}
