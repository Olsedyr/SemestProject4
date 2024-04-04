package com.example.demo.warehouse;

public class InsertItemRequest {
    private int trayId;
    private String name;

    // Getters and setters
    public int getTrayId() {
        return trayId;
    }

    public void setTrayId(int trayId) {
        this.trayId = trayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}