package com.coi.workshop.repository;

import com.coi.workshop.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Repository
public class CustomerRepository {

    private final ObjectMapper objectMapper;

    public CustomerRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Customer> getAllCustomers() {
        String jsonFile = "src/main/resources/data/customers.json";
        List<Customer> customers;
        try {
            customers = loadCustomerData(jsonFile);
        } catch (IOException e) {
            customers = Collections.emptyList();
        }
        return customers;
    }

    public List<Customer> loadCustomerData(String jsonFile) throws IOException {
        return objectMapper.readValue(new File(jsonFile), new TypeReference<>() {
        });
    }
}
