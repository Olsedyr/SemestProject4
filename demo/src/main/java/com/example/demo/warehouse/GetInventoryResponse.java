package com.example.demo.warehouse;

import java.util.List;

public class GetInventoryResponse {
    private List<String> inventory; // Assuming inventory is a list of item names
    private int state;
    private String timeStamp;

    // Constructor
    public GetInventoryResponse(List<String> inventory, int state, String timeStamp) {
        this.inventory = inventory;
        this.state = state;
        this.timeStamp = timeStamp;
    }

    // Getters and setters
    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}