package com.example.warehouse.controller;

import com.example.product.model.RecipePart;
import com.example.warehouse.endpoint.WarehouseEndpoint;
import com.example.warehouse.model.Inventory;
import com.example.warehouse.repository.InventoryRepository;
import com.example.warehouse.warehouse.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RestController()
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from frontend
public class WarehouseController {

    @Autowired
    InventoryRepository inventoryRepository;

    @Autowired
    private WarehouseEndpoint warehouseEndpoint;

    @GetMapping("/viewInventory")
    public List<Inventory> viewInventory(){

        getInventory(new GetInventory());

        Pageable pageable = PageRequest.of(0, 10); // Page 0, size 10
        List<Inventory> inventoryList = inventoryRepository.findAllByOrderByTimestamp(pageable);
        System.out.println(inventoryList);

        return inventoryList;
    }

    @GetMapping("/getInventory")
    public GetInventoryResponse getInventory(GetInventory request) {
        return warehouseEndpoint.getInventory(request);
    }

    @PostMapping("/pickItem")
    public PickItemResponse pickItem(PickItem request, @RequestParam("trayId") String trayId) {
        return warehouseEndpoint.pickItem(request);
    }

    @PostMapping("/insertItem")
    public InsertItemResponse insertItem(InsertItem request, @RequestParam("trayId") String trayId, @RequestParam("name") String name) {
        return warehouseEndpoint.insertItem(request);
    }

    @PostConstruct
    public void pickall(){
        PickItem request = new PickItem();
        for (int i = 1; i <= 10; i++) {
            request.setTrayId(i);
            warehouseEndpoint.pickItem(request);
        }

    }
    @PostConstruct
    public void fillall(List<RecipePart> recipePartList){


        // A list of the class "RecipePart" containing these items should be made, in order to use it for recipes

        List<String> warehouseItems = Arrays.asList("Small Wheels", "Small Trucks", "Small Board", "Medium Wheels", "Medium Trucks",
                "Medium Board", "Large Wheels", "Large Trucks", "Large Board", "Wheel Bearings");



        InsertItem request = new InsertItem();

        for (int i = 1; i <= 10; i++) {
            request.setTrayId(i);
            // Get the corresponding item for the current tray
            String itemName = warehouseItems.get(i - 1).toString();
            request.setName(itemName);
            warehouseEndpoint.insertItem(request);
        }

    }

}
