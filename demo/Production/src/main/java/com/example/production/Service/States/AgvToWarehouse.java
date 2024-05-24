package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.agv.agvConnection.IAgvConnectionService;
import com.example.product.repository.BatchRepository;
import com.example.production.ProductionStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// State 0 and 6
@Service
public class AgvToWarehouse {
    @Autowired
    IAgvConnectionService agvConnection;


    @Autowired
    BatchRepository batchRepository;
    ProductionStatus productionStatus = new ProductionStatus(false);

    @Transactional
    public ProductionStatus moveAgvToWarehouse() {
        agvConnection.setProgram(AgvPrograms.MoveToStorageOperation);
        agvConnection.startProgram();
        System.out.println("Program started: Move AVG to Warehouse");
        productionStatus.appendToLog("Program started: Move AVG to Warehouse");

        int attempts = 0;  // Added to prevent infinite loops

        while (attempts < 20) {
            AgvStatus status = agvConnection.getAgvStatus();
            System.out.println(status);

            // Check if the AGV is in the desired state with the correct program
            if ("MoveToStorageOperation".equals(status.getProgramName()) && status.getState() == 1) {
                System.out.println("Program finished: AGV successfully moved to Warehouse");
                productionStatus.appendToLog("Program finished: AGV successfully moved to Warehouse");
                productionStatus.setCompletedWithoutError(true);
                return productionStatus; // Exit loop and method successfully if conditions are met
            }
            attempts++;

            // a delay to prevent spamming the AGV with status requests
            try {
                Thread.sleep(1000); // Sleep for 1 second before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                productionStatus.setCompletedWithoutError(false);
                return productionStatus; // Exit if the thread is interrupted
            }
        }
        productionStatus.appendToLog("Program failed: AGV did not move to Warehouse");

        productionStatus.setCompletedWithoutError(false);
        return productionStatus;
    }


}
