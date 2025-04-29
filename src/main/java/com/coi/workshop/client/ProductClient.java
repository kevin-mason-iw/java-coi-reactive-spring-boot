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
        Optional<Product> first = productRepository.getAllProducts().stream()
            .filter(product -> product.productId().equals(productId))
            .findFirst();

        if (first.isEmpty()) {
            throw new ProductNotFoundException("Product Not Found");
        } else {
            return first.get();
        }
    }
}
