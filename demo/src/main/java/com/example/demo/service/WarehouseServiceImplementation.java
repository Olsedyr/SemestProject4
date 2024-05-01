package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;

public class WarehouseServiceImplementation implements WarehouseService{

    @Autowired
    public WarehouseServiceImplementation(IEmulatorService service) {
        this.service = service;
    }
    @Override
    public String pickItem(int trayId) {
        return null;
    }

    @Override
    public String insertItem(int trayId, String name) {
        return null;
    }

    @Override
    public String getInventory() {
        return null;
    }
}
