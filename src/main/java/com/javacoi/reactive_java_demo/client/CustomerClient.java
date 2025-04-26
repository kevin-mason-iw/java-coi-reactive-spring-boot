package com.javacoi.reactive_java_demo.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javacoi.reactive_java_demo.exceptions.CustomerNotFoundException;
import com.javacoi.reactive_java_demo.pojo.Customer;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerClient {

    private final ObjectMapper objectMapper;

    public CustomerClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Customer getCustomer(String customerId){
        Optional<Customer> customerOptional = getCustomers().stream().filter(customer -> customer.id().equals(customerId)).findFirst();
        if (customerOptional.isEmpty()){
            throw new CustomerNotFoundException("Customer Not Found");
        } else {
            return customerOptional.get();
        }
    }

    public List<Customer> getCustomers(){
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
