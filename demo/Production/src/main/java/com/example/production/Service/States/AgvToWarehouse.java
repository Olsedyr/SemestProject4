package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.production.Service.ProductionStates;
import org.springframework.stereotype.Service;

// State 0
@Service
public class AgvToWarehouse extends ProductionStates {

    AgvConnection agvConnection = AgvConnection.getInstance();

    public boolean moveAgvToWarehouse() {
        agvConnection.setProgram(AgvPrograms.MoveToStorageOperation);
        agvConnection.startProgram();
        System.out.println("Program started: Move AVG to Warehouse" );

        while (true) {
            AgvStatus status = agvConnection.getAgvStatus();
            System.out.println(status);

            // Check if the AGV is in the desired state with the correct program
            if ("MoveToStorageOperation".equals(status.getProgramName()) && status.getState() == 1) {
                System.out.println("Program finished: AGV successfully moved to Warehouse" );
                return true; // Exit loop and method successfully if conditions are met
            }

            // a delay to prevent spamming the AGV with status requests
            try {
                Thread.sleep(1000); // Sleep for 1 second before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                return false; // Exit if the thread is interrupted
            }
        }
    }



}
