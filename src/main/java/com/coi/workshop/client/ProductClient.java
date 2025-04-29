package com.coi.workshop.client;

import com.coi.workshop.repository.ProductRepository;
import com.coi.workshop.exceptions.ProductNotFoundException;
import com.coi.workshop.model.Product;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductClient {

    private final ProductRepository productRepository;

    public ProductClient(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(String productId) {
        simulateDelay();
        Optional<Product> first = productRepository.getAllProducts().stream()
            .filter(product -> product.productId().equals(productId))
            .findFirst();

        if (first.isEmpty()) {
            throw new ProductNotFoundException("Product Not Found");
        } else {
            return first.get();
        }
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
