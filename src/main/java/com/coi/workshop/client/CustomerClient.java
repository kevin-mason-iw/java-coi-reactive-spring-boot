package com.coi.workshop.client;

import com.coi.workshop.repository.CustomerRepository;
import com.coi.workshop.exceptions.CustomerNotFoundException;
import com.coi.workshop.model.Customer;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerClient {

    private final CustomerRepository customerRepository;

    public CustomerClient(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(String customerId) {
        Optional<Customer> customerOptional = customerRepository.getAllCustomers().stream()
            .filter(customer -> customer.id().equals(customerId))
            .findFirst();

        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException("Customer Not Found");
        } else {
            return customerOptional.get();
        }
    }
}
