package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.product.model.Part;
import com.example.product.model.Product;
import com.example.product.model.RecipePart;
import com.example.production.Service.ProductionStates;

import java.util.ArrayList;
import java.util.List;

// State 3
public class AgvPutAssembly extends ProductionStates {
    // Kim
    AgvConnection agvConnection = AgvConnection.getInstance();
    public List<Part> getPartList(Product product){

        // From the chosen product, a list will be made of alle the parts needed based on the product's recipe
        List<RecipePart> recipeParts = product.getRecipe().getRecipeParts();

        List<Part> partList = new ArrayList<>();
        for (RecipePart recipePart : recipeParts) {

            partList.add(recipePart.getPart());
            System.out.println("Added to part list for pick up: " + recipePart.getPart().getName());
        }
        return partList;
    }


    public boolean agvPutPart(List<Part> partList) {
        for (Part part : partList) {
            agvConnection.setProgram(AgvPrograms.PutAssemblyOperation);
            agvConnection.startProgram();
            System.out.println("Starting offloading operation of " + part.getName() );


            boolean partPlaced = false;
            int attempts = 0;  // Added to prevent infinite loops

            while (!partPlaced && attempts < 20) {
                AgvStatus status = agvConnection.getAgvStatus();
                System.out.println(status);

                if ("PutAssemblyOperation".equals(status.getProgramName()) && status.getState() == 1) {
                    partPlaced = true;  // Part picked successfully, break the inner loop
                    System.out.println(part.getName() + " placed successfully at Assembly Station");
                    break;
                }

                try {
                    Thread.sleep(1000); // Sleep for 1 second before checking again
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();  // Restore the interrupted status
                    return false;  // Exit if the thread is interrupted
                }
                attempts++;
            }

            if (!partPlaced) {
                System.out.println("Part_" + part.getName() + " not placed at Assembly Station. Operation failed.");
                return false;
            }
        }

        return true;  // All parts were placed successfully
    }
}
