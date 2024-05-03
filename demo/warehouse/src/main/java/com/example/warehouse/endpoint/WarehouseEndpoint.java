package com.example.warehouse.endpoint;

import com.example.warehouse.warehouse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Component
@Endpoint
public class WarehouseEndpoint {

    private static final String NAMESPACE_URI = "http://tempuri.org/";
    private static final String WAREHOUSE_ENDPOINT_URL = "http://localhost:8081/Service.asmx";

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetInventory")
    @ResponsePayload
    public GetInventoryResponse getInventory(@RequestPayload GetInventory request) {
        // Send request to the warehouse (docker container) to get inventory
        GetInventoryResponse response = (GetInventoryResponse) webServiceTemplate.marshalSendAndReceive(WAREHOUSE_ENDPOINT_URL, request);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "PickItem")
    @ResponsePayload
    public PickItemResponse pickItem(@RequestPayload PickItem request) {
        // Send request to the warehouse (docker container) to pick up an item
        return (PickItemResponse) webServiceTemplate.marshalSendAndReceive(WAREHOUSE_ENDPOINT_URL, request);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "InsertItem")
    @ResponsePayload
    public InsertItemResponse insertItem(@RequestPayload InsertItem request) {
        // Send request to the warehouse (docker container) to insert an item
        return (InsertItemResponse) webServiceTemplate.marshalSendAndReceive(WAREHOUSE_ENDPOINT_URL, request);
    }
}