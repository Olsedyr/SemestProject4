package com.example.demo.Assembly;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AssemblyTopics {

    @Value("${mqtt.broker}")
    private String broker;

    @Value("${mqtt.clientId}")
    private String clientId;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AssemblyTopics() {
        String broker = "tcp://localhost:1883"; // MQTT broker address
        String clientId = "your-client-id"; // Unique client ID for your MQTT client
        connectToMQTTBroker();
    }

    private void connectToMQTTBroker() {
        try {
            MqttClient mqttClient = new MqttClient(broker, clientId, new MemoryPersistence());
            mqttClient.connect();
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("Connection lost");
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    handleMQTTMessage(topic, new String(message.getPayload()));
                }

                @Override
                public void deliveryComplete(org.eclipse.paho.client.mqttv3.IMqttDeliveryToken token) {
                    // Not used in this example
                }
            });

            // Subscribe to the necessary topics
            mqttClient.subscribe("emulator/operation");
            mqttClient.subscribe("emulator/status");
            mqttClient.subscribe("emulator/checkhealth");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void handleMQTTMessage(String topic, String payload) {
        switch (topic) {
            case "emulator/operation":
                handleOperationMessage(payload);
                break;
            case "emulator/status":
                handleStatusMessage(payload);
                break;
            case "emulator/checkhealth":
                handleCheckHealthMessage(payload);
                break;
            default:
                System.out.println("Received message on unknown topic: " + topic);
        }
    }

    private void handleOperationMessage(String payload) {
        try {
            JsonNode json = objectMapper.readTree(payload);
            int processId = json.get("ProcessID").asInt();
            // Delegate processing to AssemblyStationService or handle logic here
            System.out.println("Operation message received: " + payload);
        } catch (Exception e) {
            System.out.println("Error parsing operation message: " + e.getMessage());
        }
    }

    private void handleStatusMessage(String payload) {
        try {
            JsonNode json = objectMapper.readTree(payload);
            int lastOperation = json.get("LastOperation").asInt();
            int currentOperation = json.get("CurrentOperation").asInt();
            int state = json.get("State").asInt();
            String timeStamp = json.get("TimeStamp").asText();

            // Handle status message based on parsed values
            System.out.println("Status message received: ");
            System.out.println("Last Operation: " + lastOperation);
            System.out.println("Current Operation: " + currentOperation);
            System.out.println("State: " + state);
            System.out.println("TimeStamp: " + timeStamp);
        } catch (JsonProcessingException e) {
            System.out.println("Error parsing status message: " + e.getMessage());
        }
    }

    private void handleCheckHealthMessage(String payload) {
        try {
            JsonNode json = objectMapper.readTree(payload);
            String result = json.get("result").asText();

            // Handle check health message based on parsed result
            if (result.equals("9999")) {
                // Handle unhealthy state
                System.out.println("Check health message received: Unhealthy");
            } else {
                // Handle other states if needed
                System.out.println("Check health message received: Healthy");
            }
        } catch (Exception e) {
            System.out.println("Error parsing check health message: " + e.getMessage());
        }
    }
    public void executeOperation(int processId) {
        // Implement logic to execute operation based on process ID
        switch (processId) {
            case 12345:
                startAssemblyProcess();
                break;
        }
    }

    private void startAssemblyProcess() {
    }
}