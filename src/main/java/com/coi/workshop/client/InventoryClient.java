package com.coi.workshop.client;

import com.coi.workshop.repository.InventoryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.coi.workshop.exceptions.InventoryNotFoundException;
import com.coi.workshop.model.Inventory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryClient {

    private final InventoryRepository inventoryRepository;

    public InventoryClient(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public Inventory getSku(String sku) {
        Optional<Inventory> inventoryOptional = inventoryRepository.getAllInventory().stream()
            .filter(inventory -> inventory.sku().equals(sku))
            .findFirst();

        if (inventoryOptional.isEmpty()){
            throw new InventoryNotFoundException("SKU not found in inventory.");
        } else {
            return inventoryOptional.get();
        }
    }
}
