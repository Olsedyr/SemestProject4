package com.example.production.controller;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.production.Service.StartProduction;
import com.example.production.Service.States.AgvPickParts;
import com.example.production.Service.States.AgvToWarehouse;
import com.example.warehouse.controller.WarehouseController;
import com.example.warehouse.endpoint.WarehouseEndpoint;
import com.example.warehouse.repository.InventoryRepository;
import com.example.warehouse.warehouse.PickItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(path = "api/production")
@CrossOrigin(origins = "http://localhost:3000")
@Transactional
public class ProductionController {


    AgvPickParts agvPickParts;
    AgvToWarehouse agvToWarehouse;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    private WarehouseEndpoint warehouseEndpoint;

    @Autowired
    private WarehouseController warehouseController;


    @Autowired
    public ProductionController(AgvPickParts agvPickParts, AgvToWarehouse agvToWarehouse) {
        this.agvPickParts = agvPickParts;
        this.agvToWarehouse = agvToWarehouse;
    }

    @PostMapping("/add")
    public void addProduction(@RequestParam String name) {
        Product product = productRepository.findByName(name);

        // Check if product is found
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with name: " + name);
        }

        StartProduction startProduction = new StartProduction();
        startProduction.startProduction(product);
    }
}
