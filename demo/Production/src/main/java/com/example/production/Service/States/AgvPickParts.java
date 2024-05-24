package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.agv.agvConnection.IAgvConnectionService;
import com.example.product.model.Part;
import com.example.product.model.Product;
import com.example.product.model.RecipePart;
import com.example.product.repository.BatchRepository;
import com.example.production.ProductionStatus;
import com.example.warehouse.service.IWarehouseService;
import com.example.warehouse.warehouse.PickItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

// State 1

@Service
public class AgvPickParts {
    @Autowired
    IAgvConnectionService agvConnection;
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    IWarehouseService warehouseService;

    ProductionStatus productionStatus = new ProductionStatus(false);

    public AgvPickParts() {
    }


    public List<Part> getPartList(Product product) {

        // From the chosen product, a list will be made of alle the parts needed based on the product's recipe
        List<RecipePart> recipeParts = product.getRecipe().getRecipeParts();

        List<Part> partList = new ArrayList<>();
        for (RecipePart recipePart : recipeParts) {

            partList.add(recipePart.getPart());
            System.out.println("Added to part list for pick up: " + recipePart.getPart().getName());
            productionStatus.appendToLog("Added to part list for pick up: " + recipePart.getPart().getName());
        }
        return partList;
    }


    public ProductionStatus agvPickPart(List<Part> partList) {

        for (Part part : partList) {
            agvConnection.setProgram(AgvPrograms.PickWarehouseOperation);
            agvConnection.startProgram();
            System.out.println("Starting picking operation for " + part.getName() + " at tray: " + part.getTrayId());
            productionStatus.appendToLog("Starting picking operation for " + part.getName() + " at tray: " + part.getTrayId());

            // Warehouse pick item request
            PickItemResponse response = warehouseService.pickItem(String.valueOf(part.getTrayId()));
            System.out.println(response.getPickItemResult());
            if (response.getPickItemResult().equals("Received pick operation.")) {
                //success
                System.out.println("Warehouse: part successfully picked at trayId " + part.getTrayId());
                productionStatus.appendToLog("Warehouse: part successfully picked at trayId " + part.getTrayId());
            } else {
                System.out.println("Warehouse: Failed to pick " + part.getName() + " at tray ID: " + part.getTrayId());
                productionStatus.appendToLog("Warehouse: Failed to pick " + part.getName() + " at tray ID: " + part.getTrayId());
                productionStatus.setCompletedWithoutError(false);
                return productionStatus;
            }

            boolean partPicked = false;
            int attempts = 0;  // Added to prevent infinite loops

            while (!partPicked && attempts < 20) {
                AgvStatus status = agvConnection.getAgvStatus();
                System.out.println(status);

                if ("PickWarehouseOperation".equals(status.getProgramName()) && status.getState() == 1) {
                    partPicked = true;  // Part picked successfully, break the inner loop
                    System.out.println(part.getName() + " picked successfully by AGV, at tray ID: " + part.getTrayId());
                    productionStatus.appendToLog(part.getName() + " picked successfully by AGV, at tray ID: " + part.getTrayId());

                    break;
                }

                try {
                    Thread.sleep(1000); // Sleep for 1 second before checking again
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Restore the interrupted status
                    productionStatus.setCompletedWithoutError(false);
                    return productionStatus;  // Exit if the thread is interrupted
                }
                attempts++;
            }

            if (!partPicked) {
                System.out.println("Part_" + part.getName() + " not picked. Operation failed.");
                productionStatus.appendToLog("Part_" + part.getName() + " not picked. Operation failed.");

                productionStatus.setCompletedWithoutError(false);
                return productionStatus;
            }
        }
        warehouseService.fillAll(); // Refill warehouse

        productionStatus.setCompletedWithoutError(true);
        return productionStatus;  // All parts were picked successfully
    }


}
