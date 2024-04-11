package com.example.demo.warehouse;

import java.util.List;

public class GetInventoryResponse {
    private List<String> Inventory; // Assuming inventory is a list of item names
    private int state;
    private String timeStamp;

    // Constructor
    public GetInventoryResponse(List<String> Inventory, int state, String timeStamp) {
        this.Inventory = Inventory;
        this.state = state;
        this.timeStamp = timeStamp;
    }

    // Getters and setters
    public List<String> getInventory() {
        return Inventory;
    }

    public void setInventory(List<String> Inventory) {
        this.Inventory = Inventory;
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