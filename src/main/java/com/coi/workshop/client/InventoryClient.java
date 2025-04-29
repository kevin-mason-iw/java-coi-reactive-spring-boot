package com.coi.workshop.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.coi.workshop.exceptions.InventoryNotFoundException;
import com.coi.workshop.pojo.Inventory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryClient {

    private final ObjectMapper objectMapper;

    public InventoryClient(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Inventory getInventory(String sku){
        Optional<Inventory> inventoryOptional = getAllInventory().stream().filter(inventory -> inventory.sku().equals(sku)).findFirst();

        if (inventoryOptional.isEmpty()){
            throw new InventoryNotFoundException("SKU not found in inventory.");
        } else {
            return inventoryOptional.get();
        }
    }

    public List<Inventory> getAllInventory(){
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
