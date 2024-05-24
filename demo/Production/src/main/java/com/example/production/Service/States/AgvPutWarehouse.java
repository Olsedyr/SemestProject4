package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.agv.agvConnection.IAgvConnectionService;
import com.example.product.model.Product;
import com.example.product.repository.BatchRepository;
import com.example.production.ProductionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


// State 7
@Service
public class AgvPutWarehouse {

    @Autowired
    BatchRepository batchRepository;
    @Autowired
    IAgvConnectionService agvConnection;

    ProductionStatus productionStatus = new ProductionStatus(false);


    public ProductionStatus agvPutWarehouse(Product product) {


        agvConnection.setProgram(AgvPrograms.PutWarehouseOperation);
        agvConnection.startProgram();
        System.out.println("Starting offloading operation of " + product.getName() + " at Warehouse");
        productionStatus.appendToLog("Starting offloading operation of " + product.getName() + " at Warehouse");

        boolean partPlaced = false;
        int attempts = 0;  // Added to prevent infinite loops

        while (!partPlaced && attempts < 20) {
            AgvStatus status = agvConnection.getAgvStatus();
            System.out.println(status);

            if ("PutWarehouseOperation".equals(status.getProgramName()) && status.getState() == 1) {
                partPlaced = true;  // Part picked successfully, break the inner loop
                System.out.println(product.getName() + " placed successfully at Warehouse");
                productionStatus.appendToLog(product.getName() + " placed successfully at Warehouse");
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

        if (!partPlaced) {
            System.out.println("Product " + product.getName() + " not placed at Assembly Station. Operation failed.");
            productionStatus.appendToLog("Product " + product.getName() + " not placed at Assembly Station. Operation failed.");
            productionStatus.setCompletedWithoutError(false);
            return productionStatus;
        }


        productionStatus.setCompletedWithoutError(true);
        return productionStatus;  // All parts were placed successfully
    }


}
