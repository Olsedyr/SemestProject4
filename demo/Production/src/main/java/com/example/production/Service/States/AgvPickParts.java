package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.product.model.Product;
import com.example.product.model.RecipePart;
import com.example.production.Service.ProductionStates;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// State 1
// Nikolaj
@Service
public class AgvPickParts extends ProductionStates {

    AgvConnection agvConnection = AgvConnection.getInstance();

    public AgvPickParts() {

    }


    public List<Integer> getPartList(Product product){

        System.out.println("Start production of: " + product.toString());

        // From the chosen product, a list will be made of alle the parts needed based on the product's recipe
        List<RecipePart> recipeParts = product.getRecipe().getRecipeParts();

        List<Integer> partList = new ArrayList<>();
        for (RecipePart recipePart : recipeParts) {

            partList.add(recipePart.getPart().getTrayId());
            System.out.println("AGV just picked up: " + recipePart.getPart().getName());
        }
        return partList;
    }



    public boolean agvPickPart(List<Integer> partList) {
        agvConnection.setProgram(AgvPrograms.PickWarehouseOperation);
        agvConnection.startProgram();


        while (true) {
            AgvStatus status = agvConnection.getAgvStatus();
            System.out.println(status);

            // Check if the AGV is in the desired state with the correct program
            if ("PickWarehouseOperation".equals(status.getProgramName()) && status.getState() == 1) {
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
