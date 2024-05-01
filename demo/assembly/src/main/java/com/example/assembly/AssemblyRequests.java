package com.example.assembly;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class AssemblyRequests {
    AssemblyConnection connection = AssemblyConnection.getConnection();
    MqttClient client = connection.getClient();


    public AssemblyStatus getStatus() {
        try {
            // Subscribe to the MQTT topic where status messages are published
            client.subscribe(connection.getTopics()[1], new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // Convert the received message to JSON and parse it into an AssemblyStatus object
                    String json = new String(message.getPayload());
                    AssemblyStatus status = jsonToAssemblyStatus(json);

                    System.out.println("Received Assembly Status: " + status);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return null; // Asynchronous operation, return null for now
    }

    public String getHealthCheck() {
        try {
            client.subscribe(connection.getTopics()[2], new IMqttMessageListener() {
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {

                    System.out.println("Received Assembly Health Check: " + message);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return null; // Asynchronous operation, return null for now
    }

    public void executeProgram(int processId) {

        String payload = "{\"ProcessID\": "+processId+"}";
        MqttMessage mqttMessage = new MqttMessage(payload.getBytes());
        mqttMessage.setQos(2); // Quality of Service (QoS): 2 = exactly once

        try {
            client.publish(connection.getTopics()[0], mqttMessage);
        } catch (MqttException e) {
            System.err.println("Error: program was not executed");
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
        assemblyRequests.getStatus();
        assemblyRequests.getHealthCheck();
        assemblyRequests.executeProgram(100);

    }
}
