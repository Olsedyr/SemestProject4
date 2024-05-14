package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.product.model.Batch;
import com.example.product.repository.BatchRepository;
import com.example.production.ProductionStatus;
import com.example.production.Service.ProductionStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// State 0
@Service
public class AgvToCharger extends ProductionStates {

    AgvConnection agvConnection = AgvConnection.getInstance();
    @Autowired
    BatchRepository batchRepository;

    public ProductionStatus moveAgvToCharger(int batteryPercentage, Batch batch) {
        ProductionStatus productionStatus = new ProductionStatus(false);
        AgvStatus status = agvConnection.getAgvStatus();
        // Skip the charging phase if status.getBattery() > batteryPercentage
        if (status.getBattery() > batteryPercentage) {
            System.out.println("Battery level is sufficient, skipping charging phase");
            batch.appendToLog("Battery level is sufficient, skipping charging phase");
            batchRepository.save(batch);
            productionStatus.setCompletedWithoutError(true);
            return productionStatus;
        }
        agvConnection.setProgram(AgvPrograms.MoveToChargerOperation);
        agvConnection.startProgram();
        System.out.println("Program started: Move AVG to Charging Station");
        batch.appendToLog("Program started: Move AVG to Charging Station");
        batchRepository.save(batch);

        int attempts = 0;  // Added to prevent infinite loops

        while (attempts < 20) {
            status = agvConnection.getAgvStatus();
            System.out.println(status);

            // Check if the AGV is in the desired state with the correct program
            if ("MoveToChargerOperation".equals(status.getProgramName()) && status.getState() == 1 && status.getBattery() == 100) {
                System.out.println("Program finished: AGV is fully charged");
                batch.appendToLog("Program finished: AGV is fully charged");
                batchRepository.save(batch);
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
        productionStatus.setCompletedWithoutError(true);
        return productionStatus;
    }


}
