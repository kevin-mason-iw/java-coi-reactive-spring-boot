package com.coi.workshop.repository;

import com.coi.workshop.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository {

    private final ObjectMapper objectMapper;

    public ProductRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Product> getAllProducts() {
        String jsonFile = "src/main/resources/data/products.json";
        List<Product> products;
        try {
            products = loadProductData(jsonFile);
        } catch (IOException e) {
            products = Collections.emptyList();
        }
        return products;
    }


    public List<Product> loadProductData(String jsonFile) throws IOException {
        return objectMapper.readValue(new File(jsonFile), new TypeReference<>() {
        });
    }
}
