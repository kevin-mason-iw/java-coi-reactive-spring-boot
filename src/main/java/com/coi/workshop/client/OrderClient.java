package com.coi.workshop.client;

import com.coi.workshop.repository.OrderRepository;
import com.coi.workshop.model.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderClient {

    private final OrderRepository orderRepository;


    public OrderClient(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        simulateDelay();
        return orderRepository.getAllOrders();
    }

    /**
     * Task 3. Make request to the OrderService using webclient
     * git clone git@github.com:kevin-mason-iw/java-coi-reactive-order-service.git
     * return Order
     */
    public Order getOrder(String orderNumber){
        return null;
    }

    private void simulateDelay() {
        try {
            Thread.sleep((long) (Math.random() * 10)); // Simulate a random delay up to 10ms
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // Restore interrupted status
            throw new RuntimeException("Thread was interrupted", e);
        }
    }
}
