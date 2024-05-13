package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.production.Service.ProductionStates;

//State 2
public class AgvToAssembly extends ProductionStates {
    AgvConnection agvConnection = AgvConnection.getInstance();

    public boolean moveAgvToAssembly() {
        agvConnection.setProgram(AgvPrograms.MoveToAssemblyOperation);
        agvConnection.startProgram();
        System.out.println("Program started: Move AVG to Assembly Station" );
        int attempts = 0;  // Added to prevent infinite loops

        while (attempts<20) {
            AgvStatus status = agvConnection.getAgvStatus();
            System.out.println(status);
            attempts++;
            // Check if the AGV is in the desired state with the correct program
            if ("MoveToAssemblyOperation".equals(status.getProgramName()) && status.getState() == 1) {
                System.out.println("Program finished: AGV successfully moved to assembly Station" );
                return true; // Exit loop and method successfully if conditions are met
            }
            attempts++;

            // a delay to prevent spamming the AGV with status requests
            try {
                Thread.sleep(1000); // Sleep for 1 second before checking again
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore the interrupted status
                return false; // Exit if the thread is interrupted
            }
        }
        return false;
    }
}
