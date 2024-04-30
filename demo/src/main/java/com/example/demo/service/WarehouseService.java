package com.example.demo.service;

import com.example.demo.repository.TestDBRepository;
import com.example.demo.warehouse.GetInventoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {
/*

    TestDBRepository testDBRepository;

    @Autowired
    public WarehouseService(TestDBRepository testDBRepository) {
            this.testDBRepository = testDBRepository;
        }

    public List<GetInventoryResponse.Inventory> getInventory() {
        List<GetInventoryResponse.Inventory> inventoryList = testDBRepository.findAll(); // Assuming Inventory is the entity returned by testDBRepository

        // Convert Inventory entities to GetInventoryResponse.Inventory objects
        List<GetInventoryResponse.Inventory> responseInventoryList = inventoryList.stream()
                .map(this::convertToResponseInventory)
                .collect(Collectors.toList());

        return responseInventoryList;
    }

    // Helper method to convert Inventory entity to GetInventoryResponse.Inventory object
    private GetInventoryResponse.Inventory convertToResponseInventory(GetInventoryResponse.Inventory inventory) {
        GetInventoryResponse.Inventory responseInventory = new GetInventoryResponse.Inventory();
        // Set properties of responseInventory based on inventory entity
        responseInventory.setId(inventory.getId());
        responseInventory.setName(inventory.getName());
        // Map other properties as needed

        return responseInventory;
    }



    // Method to get current timestamp
    public String getCurrentTimeStamp() {
        // Method  to get the current timestamp
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return currentTime.format(formatter);
    }

    // Method to handle picking an item from the warehouse
    public void pickItem(int trayId) {

    }

    // Method to handle inserting an item into the warehouse
    public void insertItem(int trayId, String itemName) {

    }
    
 */
}
