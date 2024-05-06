package com.example.warehouse;

import com.example.warehouse.endpoint.WarehouseEndpoint;
import com.example.warehouse.model.Inventory;
import com.example.warehouse.repository.InventoryRepository;
import com.example.warehouse.warehouse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WarehouseController {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    private WarehouseEndpoint warehouseEndpoint;

    @GetMapping("/viewInventory")
    public List<Inventory> viewInventory(){

        Pageable pageable = PageRequest.of(0, 10); // Page 0, size 10
        List<Inventory> inventoryList = inventoryRepository.findAllByOrderByTimestamp(pageable);
        System.out.println(inventoryList);

        return inventoryList;
    }

    @GetMapping("/getInventory")
    public GetInventoryResponse getInventory(GetInventory request) {
        return warehouseEndpoint.getInventory(request);
    }

    @PostMapping("/pickItem")
    public PickItemResponse pickItem(@RequestBody PickItem request) {
        return warehouseEndpoint.pickItem(request);
    }

    @PostMapping("/insertItem")
    public InsertItemResponse insertItem(@RequestBody InsertItem request) {
        return warehouseEndpoint.insertItem(request);
    }
}
