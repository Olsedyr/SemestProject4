package com.example.demo.service;

public interface WarehouseService {
    String pickItem(int trayId);
    String insertItem(int trayId, String name);
    String getInventory();
}
