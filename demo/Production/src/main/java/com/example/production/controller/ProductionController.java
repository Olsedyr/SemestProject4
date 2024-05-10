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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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
    public String addProduction(@RequestParam String name) {
        Product product = productRepository.findByName(name);
        StartProduction startProduction = new StartProduction();
        startProduction.startProduction(product);


        // The items will be picked from the warehouse
        PickItem request = new PickItem();

        List<Integer> partList = agvPickParts.getPartList(product);

        for (int i = 0; i < partList.size(); i++) {

            int trayID = partList.get(i);
            request.setTrayId(trayID);
            warehouseEndpoint.pickItem(request);

            System.out.println("A part has been picked up from trayID: " + trayID);

            // Delete inventory record for this trayId
            //inventoryRepository.deleteInventoryByTrayID(trayID);

        }

        // Fill all trays with parts again as quantity has not yet been implemented
        // remove this method call to see the warehouse trays get emptied
        warehouseController.fillall();
        System.out.println("Trays have automatically been filled (NO QUANTITY YET)");

        return null;
    }
}
