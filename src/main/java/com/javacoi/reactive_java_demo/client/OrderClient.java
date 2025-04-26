package com.javacoi.reactive_java_demo.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javacoi.reactive_java_demo.exceptions.OrderNotFoundException;
import com.javacoi.reactive_java_demo.pojo.Order;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class OrderClient {

    private final ObjectMapper objectMapper;

    public OrderClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Order getOrder(String orderNumber){
        Optional<Order> orderOptional = getOrders().stream().filter(order -> order.OrderNumber().equals(orderNumber)).findFirst();

        if (orderOptional.isEmpty()){
            throw new OrderNotFoundException("Order Not Found");
        } else {
            return orderOptional.get();
        }
    }

    public List<Order> getOrders(){
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
