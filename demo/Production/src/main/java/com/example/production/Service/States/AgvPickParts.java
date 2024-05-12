package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.product.model.Part;
import com.example.product.model.Product;
import com.example.product.model.RecipePart;
import com.example.production.Service.ProductionStates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

// State 1
// Nikolaj
@Service
public class AgvPickParts extends ProductionStates {

    AgvConnection agvConnection = AgvConnection.getInstance();

    public AgvPickParts() {

    }


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


    public boolean agvPickPart(List<Part> partList) {
        for (Part part : partList) {
            agvConnection.setProgram(AgvPrograms.PickWarehouseOperation);
            agvConnection.startProgram();
            System.out.println("Starting picking operation for " + part.getName() + " at tray: " + part.getTrayId() );

            pickItem(String.valueOf(part.getTrayId())); //  Pick part from warehouse at trayId

            boolean partPicked = false;
            int attempts = 0;  // Added to prevent infinite loops

            while (!partPicked && attempts < 20) {
                AgvStatus status = agvConnection.getAgvStatus();
                System.out.println(status);

                if ("PickWarehouseOperation".equals(status.getProgramName()) && status.getState() == 1) {
                    partPicked = true;  // Part picked successfully, break the inner loop
                    System.out.println(part.getName() + " picked successfully by AGV, at tray ID: " + part.getTrayId());
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

            if (!partPicked) {
                System.out.println("Part_" + part.getName() + " not picked. Operation failed.");
                return false;
            }
        }
        fillAll(); // Refill warehouse
        return true;  // All parts were picked successfully
    }




    public void pickItem(String trayId) {
        try {
            URL url = new URL("http://localhost:8080/pickItem");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Write parameters to the request body
            String postData = "trayId=" + trayId;
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(postData);
                wr.flush();
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Warehouse: part successfully picked at trayId " + trayId );
            } else {
                System.out.println("Failed to pick part at warehouse: HTTP status " + responseCode);
            }

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error during communication with the server.");
        }
    }

    private void fillAll() {
        try {
            URL url = new URL("http://localhost:8080/fillAll");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.disconnect();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Trays have automatically been filled (NO QUANTITY YET)" );
            } else {
                System.out.println("Failed to fill warehouse: HTTP status " + responseCode);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
