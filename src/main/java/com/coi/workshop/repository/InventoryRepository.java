package com.coi.workshop.repository;

import com.coi.workshop.model.Inventory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Repository
public class InventoryRepository {

    private final ObjectMapper objectMapper;

    public InventoryRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<Inventory> getAllInventory() {
        String jsonFile = "src/main/resources/data/inventory.json";
        List<Inventory> inventoryList;
        try {
            inventoryList = loadInventoryData(jsonFile);
        } catch (IOException e) {
            inventoryList = Collections.emptyList();
        }
        return inventoryList;
    }

    public List<Inventory> loadInventoryData(String jsonFile) throws IOException {
        return objectMapper.readValue(new File(jsonFile), new TypeReference<>() {
        });
    }
}
