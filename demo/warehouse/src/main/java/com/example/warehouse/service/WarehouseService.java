package com.example.warehouse.service;


import com.example.product.model.Part;
import com.example.product.repository.PartRepository;
import com.example.warehouse.endpoint.WarehouseEndpoint;
import com.example.warehouse.model.Inventory;
import com.example.warehouse.repository.InventoryRepository;
import com.example.warehouse.warehouse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private WarehouseEndpoint warehouseEndpoint;

    @Autowired
    private PartRepository partRepository;

    @Override
    public GetInventoryResponse getInventory() {
        return warehouseEndpoint.getInventory(new GetInventory());
    }

    @Override
    public PickItemResponse pickItem(String trayId) {
        PickItem request = new PickItem();
        request.setTrayId(Integer.parseInt(trayId));
        return warehouseEndpoint.pickItem(request);
    }

    @Override
    public InsertItemResponse insertItem(String trayId, String name) {
        InsertItem request = new InsertItem();
        request.setTrayId(Integer.parseInt(trayId));
        request.setName(name);
        return warehouseEndpoint.insertItem(request);
    }


    @Override
    public void fillAll() {
        Pageable topTen = PageRequest.of(0, 10);
        List<Part> parts = partRepository.findAll(topTen).getContent();  // fill with the first 10 parts from the database

        InsertItem request = new InsertItem();

        for (int i = 0; i < parts.size(); i++) {
            Part part = parts.get(i);
            request.setTrayId(i+1);
            request.setName(part.getName());
            warehouseEndpoint.insertItem(request);
        }
    }

    @Override
    public void pickAll(){
        PickItem request = new PickItem();
        for (int i = 1; i <= 10; i++) {
            request.setTrayId(i);
            warehouseEndpoint.pickItem(request);
        }

    }

    @PostConstruct
    public void postConstruct(){
        pickAll();
        fillAll();
    }
}