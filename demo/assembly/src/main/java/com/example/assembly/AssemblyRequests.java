package com.example.assembly;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AssemblyRequests {
    AssemblyConnection connection = AssemblyConnection.getConnection();
    MqttClient client = connection.getClient();

    public void getStatus(StatusCallback callback) {
        try {
            // Subscribe to the MQTT topic where status messages are published
            client.subscribe(connection.getTopics()[1], (topic, message) -> {
                // Convert the received message to JSON and parse it into an AssemblyStatus object
                String json = new String(message.getPayload());
                AssemblyStatus status = jsonToAssemblyStatus(json);
                callback.onStatusReceived(status);
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void getHealthCheck(StatusCallback callback) {
        try {
            client.subscribe(connection.getTopics()[2], (topic, message) -> {
                String health = new String(message.getPayload());
                callback.onHealthReceived(health);
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public interface StatusCallback {
        void onStatusReceived(AssemblyStatus status);

        void onHealthReceived(String health);
    }


    public void executeProgram(Long processId) {

        String payload = "{\"ProcessID\": " + processId + "}";
        MqttMessage mqttMessage = new MqttMessage(payload.getBytes());
        mqttMessage.setQos(2); // Quality of Service (QoS): 2 = exactly once

        try {
            client.publish(connection.getTopics()[0], mqttMessage);
        } catch (MqttException e) {
            System.err.println("Error: program was not executed");
        }
    }


    // Method to unsubscribe from the status topic
    public void unsubscribeStatus() {
        try {
            client.unsubscribe(connection.getTopics()[1]);
            System.out.println("Unsubscribed from status topic");
        } catch (MqttException e) {
            System.err.println("Error unsubscribing from status topic: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to unsubscribe from the health check topic
    public void unsubscribeHealthCheck() {
        try {
            client.unsubscribe(connection.getTopics()[2]);
            System.out.println("Unsubscribed from health check topic");
        } catch (MqttException e) {
            System.err.println("Error unsubscribing from health check topic: " + e.getMessage());
            e.printStackTrace();
        }
    }


    private AssemblyStatus jsonToAssemblyStatus(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            // Deserialize JSON string to AssemblyStatus object
            AssemblyStatus assemblyStatus = objectMapper.readValue(json, AssemblyStatus.class);

            return assemblyStatus;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // for testing
    public static void main(String[] args) {
        AssemblyRequests assemblyRequests = new AssemblyRequests();
        AssemblyRequests.StatusCallback callback = new StatusCallback() {
            @Override
            public void onStatusReceived(AssemblyStatus status) {
                System.out.println("Received Assembly Status: " + status);
            }

            @Override
            public void onHealthReceived(String health) {
                System.out.println("Received Assembly Health Check: " + health);
            }
        };
        assemblyRequests.getStatus(callback);
        assemblyRequests.getHealthCheck(callback);
        assemblyRequests.executeProgram(100L);

    }
}
