package com.example.demo.endpoint;

import com.example.demo.model.testDB;
import com.example.demo.service.WarehouseService;
import com.example.demo.warehouse.GetInventoryResponse;
import com.example.demo.warehouse.InsertItemRequest;
import com.example.demo.warehouse.PickItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
@Component
public class WarehouseEndpoint {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseEndpoint(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PayloadRoot(namespace = "http://example.org/warehouse", localPart = "PickItem")
    public void pickItem(@RequestPayload PickItemRequest request) {
        // Logic to request an item from the warehouse
    }

    @PayloadRoot(namespace = "http://example.org/warehouse", localPart = "InsertItem")
    public void insertItem(@RequestPayload InsertItemRequest request) {
        // Logic to insert an item into the warehouse
    }

    @PayloadRoot(namespace = "http://example.org/warehouse", localPart = "GetInventory")
    @ResponsePayload
    public GetInventoryResponse getInventory() {
        GetInventoryResponse response = new GetInventoryResponse();
        // Retrieve inventory data from the service
        List<GetInventoryResponse.Inventory> inventoryList = warehouseService.getInventory();
        // Add inventory data to the response
        response.setInventory((GetInventoryResponse.Inventory) inventoryList);
        return response;
    }
}
