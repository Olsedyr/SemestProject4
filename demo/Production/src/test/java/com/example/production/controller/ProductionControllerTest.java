package com.example.production.controller;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.production.Service.StartProduction;
import com.example.production.Service.States.AgvPickParts;
import com.example.production.Service.States.AgvToWarehouse;
import com.example.warehouse.controller.WarehouseController;
import com.example.warehouse.endpoint.WarehouseEndpoint;
import com.example.warehouse.repository.InventoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class ProductionControllerTest {

    private ProductionController productionController;

    @Mock
    private AgvPickParts mockAgvPickParts;

    @Mock
    private AgvToWarehouse mockAgvToWarehouse;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private InventoryRepository mockInventoryRepository;

    @Mock
    private WarehouseEndpoint mockWarehouseEndpoint;

    @Mock
    private WarehouseController mockWarehouseController;

    @Mock
    private StartProduction mockStartProduction;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productionController = new ProductionController(mockAgvPickParts, mockAgvToWarehouse);
        productionController.productRepository = mockProductRepository;
        productionController.inventoryRepository = mockInventoryRepository;
        productionController.warehouseEndpoint = mockWarehouseEndpoint;
        productionController.warehouseController = mockWarehouseController;
        productionController.startProduction = mockStartProduction;
    }

    @Test
    void addProduction_productNotFound_shouldThrowException() {
        // Arrange
        String productName = "Test Product";
        when(mockProductRepository.findByName(anyString())).thenReturn(null);

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            productionController.addProduction(productName);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Product not found with name: " + productName, exception.getReason());
        verify(mockProductRepository, times(1)).findByName(productName);
        verifyNoInteractions(mockStartProduction);
    }

    @Test
    void addProduction_productFound_shouldStartProduction() {
        // Arrange
        String productName = "Test Product";
        Product product = new Product();
        product.setName(productName);
        when(mockProductRepository.findByName(anyString())).thenReturn(product);

        // Act
        productionController.addProduction(productName);

        // Assert
        verify(mockProductRepository, times(1)).findByName(productName);
        verify(mockStartProduction, times(1)).startProduction(product);
    }
}