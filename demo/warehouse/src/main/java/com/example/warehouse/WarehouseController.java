package com.example.warehouse;

import com.example.warehouse.endpoint.WarehouseEndpoint;
import com.example.warehouse.warehouse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WarehouseController {

    @Autowired
    private WarehouseEndpoint warehouseEndpoint;

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
