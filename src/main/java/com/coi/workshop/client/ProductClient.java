package com.coi.workshop.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.coi.workshop.exceptions.ProductNotFoundException;
import com.coi.workshop.pojo.Product;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ProductClient {

    private final ObjectMapper objectMapper;

    public ProductClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Product getProduct(String productId) {
        Optional<Product> first = getProducts().stream().filter(product -> product.productId().equals(productId)).findFirst();
        if (first.isEmpty()){
            throw new ProductNotFoundException("Product Not Found");
        } else {
            return first.get();
        }
    }

    public List<Product> getProducts(){
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
