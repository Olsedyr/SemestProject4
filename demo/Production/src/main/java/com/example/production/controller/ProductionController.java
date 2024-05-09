package com.example.production.controller;

import com.example.product.dto.RecipeDTO;
import com.example.product.model.Product;
import com.example.product.model.Recipe;
import com.example.production.Service.StartProduction;
import com.example.production.Service.States.AgvPickParts;
import com.example.production.Service.States.AgvToWarehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/production")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductionController {

    AgvPickParts agvPickParts;
    AgvToWarehouse agvToWarehouse;


    @Autowired
    public ProductionController(AgvPickParts agvPickParts, AgvToWarehouse agvToWarehouse) {
        this.agvPickParts = agvPickParts;
        this.agvToWarehouse = agvToWarehouse;
    }

    @PostMapping("/add")
    public String addProduction(@RequestBody String productName) {
        StartProduction startProduction = new StartProduction();
        startProduction.startProduction(productName);
        return null;
    }
}
