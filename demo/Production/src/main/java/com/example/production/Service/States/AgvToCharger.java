package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.production.Service.ProductionStates;
import org.springframework.stereotype.Service;

// State 0
@Service
public class AgvToCharger extends ProductionStates {

    AgvConnection agvConnection = AgvConnection.getInstance();


    public boolean moveAgvToCharger(int batteryPercentage) {
        AgvStatus status = agvConnection.getAgvStatus();
        // Skip the charging phase if status.getBattery() > batteryPercentage
        if (status.getBattery() > batteryPercentage){
            System.out.println("Battery level is sufficient, skipping charging phase" );
            return true;
        }
        agvConnection.setProgram(AgvPrograms.MoveToChargerOperation);
        agvConnection.startProgram();
        System.out.println("Program started: Move AVG to Charging Station" );
        int attempts = 0;  // Added to prevent infinite loops

        while (attempts<20) {
            status = agvConnection.getAgvStatus();
            System.out.println(status);

            // Check if the AGV is in the desired state with the correct program
            if ("MoveToChargerOperation".equals(status.getProgramName()) && status.getState() == 1 && status.getBattery()==100) {
                System.out.println("Program finished: AGV is fully charged" );
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
