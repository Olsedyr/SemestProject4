package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WarehouseService {

    // nothing is implemented yet

    public WarehouseService() {

    }

    // Method to get inventory data
    public List<String> GetInventory() {

        return null;
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
}
