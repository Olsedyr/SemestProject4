package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.agv.agvConnection.IAgvConnectionService;
import com.example.product.model.Product;
import com.example.product.repository.BatchRepository;
import com.example.production.ProductionStatus;
import com.example.warehouse.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// State 5

@Service
public class AgvPickAssembly {
    @Autowired
    IAgvConnectionService agvConnection;
    @Autowired
    BatchRepository batchRepository;
    @Autowired
    IWarehouseService warehouseService;



    public AgvPickAssembly() {
    }

    public ProductionStatus agvPickAssembly(Product product) {

        ProductionStatus productionStatus = new ProductionStatus(false);
        agvConnection.setProgram(AgvPrograms.PickAssemblyOperation);
        agvConnection.startProgram();
        System.out.println("Starting picking operation for " + product.getName() + " at the assembly station");
        productionStatus.appendToLog("Starting picking operation for " + product.getName() + " at the assembly station");


        boolean partPicked = false;
        int attempts = 0;  // Added to prevent infinite loops

        while (!partPicked && attempts < 20) {
            AgvStatus status = agvConnection.getAgvStatus();
            System.out.println(status);

            if ("PickAssemblyOperation".equals(status.getProgramName()) && status.getState() == 1) {
                partPicked = true;  // Part picked successfully, break the inner loop
                System.out.println(product.getName() + " picked successfully by AGV, at the assembly station");
                productionStatus.appendToLog(product.getName() + " picked successfully by AGV, at the assembly station");
                break;
            }

            try {
                Thread.sleep(1000); // Sleep for 1 second before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                productionStatus.setCompletedWithoutError(false);
                return productionStatus;  // Exit if the thread is interrupted
            }
            attempts++;
        }

        if (!partPicked) {
            System.out.println("Product" + product.getName() + " not picked. Operation failed.");
            productionStatus.appendToLog("Product" + product.getName() + " not picked. Operation failed.");

            productionStatus.setCompletedWithoutError(false);
            return productionStatus;
        }

        productionStatus.setCompletedWithoutError(true);
        return productionStatus;
    }


}
