package com.example.production.controller;

import com.example.product.model.Product;
import com.example.product.repository.ProductRepository;
import com.example.production.Service.StartProduction;
import com.example.production.Service.States.AgvPickParts;
import com.example.production.Service.States.AgvToWarehouse;
import com.example.warehouse.endpoint.WarehouseEndpoint;
import com.example.warehouse.warehouse.PickItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/production")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductionController {


    AgvPickParts agvPickParts;
    AgvToWarehouse agvToWarehouse;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private WarehouseEndpoint warehouseEndpoint;


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

        PickItem request = new PickItem();

        List<String> partList = agvPickParts.getPartList(product);

        for (int i = 0; i <= partList.size(); i++) {

            request.setTrayId(i);
            warehouseEndpoint.pickItem(request);
        }

        return null;
    }
}
