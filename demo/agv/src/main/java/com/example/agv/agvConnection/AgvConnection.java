package com.example.agv.agvConnection;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class AgvConnection implements IAgvConnectionService {

    private static final AgvConnection INSTANCE = new AgvConnection();
    private final String url = "http://localhost:8082/v1/status/";
    private final RestTemplate restTemplate;

    private AgvConnection() {
        this.restTemplate = new RestTemplate();
    }

    public static AgvConnection getInstance() {
        return INSTANCE;
    }

    @Override
    public void setProgram(AgvPrograms program) {
        // Create an HTTP connection
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);

            // HTTP request body
            String input;
            input = "{\"Program name\":\"" + program.value + "\", \"state\":1}";

            // Send the request
            connection.getOutputStream().write(input.getBytes());

            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.err.println(responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startProgram() {
        // Create an HTTP connection
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setDoOutput(true);

            // HTTP request body
            String input;
            input = "{\"state\":2}";

            // Send the request
            connection.getOutputStream().write(input.getBytes());

            // Check the response code
            int responseCode = connection.getResponseCode();
            if (responseCode != 200) {
                System.err.println(responseCode);
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AgvStatus getAgvStatus() {
        return restTemplate.getForObject(url, AgvStatus.class);
    }

    // Just to test
    public static void main(String[] args) {
        AgvConnection agvConnection = AgvConnection.getInstance();
        //agvConnection.setProgram(AgvPrograms.MoveToStorageOperation);
        agvConnection.setProgram(AgvPrograms.PickWarehouseOperation);
        agvConnection.startProgram();
        System.out.println(agvConnection.getAgvStatus());
    }

}