package com.example.demo.service;

import com.example.demo.repository.TestDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class WarehouseService {


    TestDBRepository testDBRepository;

    @Autowired
    public WarehouseService(TestDBRepository testDBRepository) {
            this.testDBRepository = testDBRepository;
        }
/*
    // Assume this method retrieves inventory data from your warehouse
    public List<GetInventoryResponse.Inventory> retrieveInventoryData() {
        // Implement logic to retrieve inventory data
        WarehouseEndpoint
        // This could be fetching from a database, an external API, or any other data source
    }


    public GetInventoryResponse.Inventory getInventory() {
        // Retrieve inventory data from your warehouse
        List<GetInventoryResponse.Inventory> inventoryData = retrieveInventoryData();

        // Create a new instance of GetInventoryResponse.Inventory
        GetInventoryResponse.Inventory inventory = new GetInventoryResponse.Inventory();

        // Set the inventory data to the Inventory object
        inventory.getInventoryItem().addAll(inventoryData);

        // Return the populated Inventory object
        return inventory;
    }

 */



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


}
