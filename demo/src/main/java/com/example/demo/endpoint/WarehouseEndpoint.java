// WarehouseEndpoint.java
package com.example.demo.endpoint;

import com.example.demo.service.WarehouseService;
import com.example.demo.warehouse.GetInventoryResponse;
import com.example.demo.warehouse.InsertItemResponse;
import com.example.demo.warehouse.PickItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@Component
public class WarehouseEndpoint {

    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseEndpoint(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PayloadRoot(namespace = "http://tempuri.org/", localPart = "PickItem")
    @ResponsePayload
    public PickItemResponse PickItem() {
        PickItemResponse response = new PickItemResponse();
        warehouseService.pickItem(response.getTrayId());

        return response;
    }

    @PayloadRoot(namespace = "http://tempuri.org/", localPart = "InsertItem")
    @ResponsePayload
    public InsertItemResponse InsertItem() {
        InsertItemResponse response = new InsertItemResponse();
        System.out.println(response);
        return response;
    }

    @PayloadRoot(namespace = "http://tempuri.org/", localPart = "GetInventory")
    @ResponsePayload
    public GetInventoryResponse GetInventory() {
        GetInventoryResponse response = new GetInventoryResponse();
        response.setState(1);
        System.out.println(response.toString());
        return response;
    }
}
