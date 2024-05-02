package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import warehouse.*;

public class WarehouseServiceClient extends WebServiceGatewaySupport implements WarehouseService {

    @Value("${warehouse.service.endpoint}")
    private String endpointUrl;

    @Override
    public String pickItem(int trayId) {
        PickItem request = new PickItem();
        request.setTrayId(trayId);
        PickItemResponse response = (PickItemResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getPickItemResult();
    }

    @Override
    public String insertItem(int trayId, String name) {
        InsertItem request = new InsertItem();
        request.setTrayId(trayId);
        request.setName(name);
        InsertItemResponse response = (InsertItemResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response.getInsertItemResult();
    }

    @Override
    public String getInventory() {
        GetInventory request = new GetInventory();
        GetInventoryResponse response = (GetInventoryResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        System.out.println(response.getGetInventoryResult());
        return response.getGetInventoryResult();
    }
}
