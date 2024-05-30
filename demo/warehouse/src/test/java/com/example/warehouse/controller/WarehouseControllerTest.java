package com.example.warehouse.controller;

import com.example.warehouse.endpoint.WarehouseEndpoint;
import com.example.warehouse.model.Inventory;
import com.example.warehouse.repository.InventoryRepository;
import com.example.warehouse.warehouse.GetInventory;
import com.example.warehouse.warehouse.GetInventoryResponse;
import com.example.warehouse.warehouse.InsertItem;
import com.example.warehouse.warehouse.InsertItemResponse;
import com.example.warehouse.warehouse.PickItem;
import com.example.warehouse.warehouse.PickItemResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class WarehouseControllerTest {

    @Mock
    private InventoryRepository inventoryRepository;

    @Mock
    private WarehouseEndpoint warehouseEndpoint;

    @InjectMocks
    private WarehouseController warehouseController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testViewInventory() {
        // Mock data
        List<Inventory> inventoryList = new ArrayList<>();
        // Add inventory items to the list
        // ...

        // Mock repository method
        when(inventoryRepository.findAllByOrderByTimestamp(any(Pageable.class)))
                .thenReturn((List<Inventory>) new PageImpl<>(inventoryList));

        // Call controller method
        List<Inventory> result = warehouseController.viewInventory();

        // Verify that the repository method was called
        verify(inventoryRepository, times(1)).findAllByOrderByTimestamp(any(Pageable.class));

        // Assert that the result matches the mock data
        assertEquals(inventoryList, result);
    }

    @Test
    void testGetInventory() {
        // Mock request
        GetInventory request = new GetInventory();
        // Mock response
        GetInventoryResponse response = new GetInventoryResponse();
        // Set response data
        // ...

        // Mock endpoint method
        when(warehouseEndpoint.getInventory(request)).thenReturn(response);

        // Call controller method
        GetInventoryResponse result = warehouseController.getInventory(request);

        // Verify that the endpoint method was called
        verify(warehouseEndpoint, times(1)).getInventory(request);

        // Assert that the result matches the mock response
        assertEquals(response, result);
    }


}