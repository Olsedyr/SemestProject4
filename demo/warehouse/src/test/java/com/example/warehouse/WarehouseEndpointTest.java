package com.example.warehouse;

import com.example.warehouse.endpoint.WarehouseEndpoint;
import com.example.warehouse.warehouse.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WarehouseEndpointTest {

    @Mock
    private WebServiceTemplate webServiceTemplateMock;

    @InjectMocks
    private WarehouseEndpoint warehouseEndpoint;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetInventory() {
        GetInventory request = new GetInventory();
        GetInventoryResponse response = new GetInventoryResponse();

        when(webServiceTemplateMock.marshalSendAndReceive(any(String.class), any(GetInventory.class))).thenReturn(response);

        GetInventoryResponse result = warehouseEndpoint.getInventory(request);

        verify(webServiceTemplateMock, times(1)).marshalSendAndReceive(any(String.class), any(GetInventory.class));
        // Add your assertions
    }

    @Test
    public void testPickItem() {
        PickItem request = new PickItem();
        PickItemResponse response = new PickItemResponse();

        when(webServiceTemplateMock.marshalSendAndReceive(any(String.class), any(PickItem.class))).thenReturn(response);

        PickItemResponse result = warehouseEndpoint.pickItem(request);

        verify(webServiceTemplateMock, times(1)).marshalSendAndReceive(any(String.class), any(PickItem.class));
        // Add your assertions
    }

    @Test
    public void testInsertItem() {
        InsertItem request = new InsertItem();
        InsertItemResponse response = new InsertItemResponse();

        when(webServiceTemplateMock.marshalSendAndReceive(any(String.class), any(InsertItem.class))).thenReturn(response);

        InsertItemResponse result = warehouseEndpoint.insertItem(request);

        verify(webServiceTemplateMock, times(1)).marshalSendAndReceive(any(String.class), any(InsertItem.class));
        // Add your assertions
    }
}