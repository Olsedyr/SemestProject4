package com.example.assembly;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class AssemblyConnection {

    private static AssemblyConnection instance;

    private String broker = "tcp://localhost:1883";
    private String clientId = "client-id";
    private String[] topics = new String[]{
            "emulator/operation",
            "emulator/status",
            "emulator/checkhealth"
    };
    private MqttClient client;

    private AssemblyConnection() {
        try {
            client = new MqttClient(broker, clientId, new MemoryPersistence());
            client.connect();
        } catch (MqttException e) {
            System.err.println("Failed to connect to MQTT");
        }
    }

    public static AssemblyConnection getConnection() {
        if (instance == null) {
            synchronized (AssemblyConnection.class) {
                if (instance == null) {
                    instance = new AssemblyConnection();
                }
            }
        }
        return instance;
    }

    public String getBroker() {
        return broker;
    }

    public void setBrokerUrl(String brokerUrl) {
        this.broker = broker;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }

    public MqttClient getClient() {
        return client;
    }

    public void setClient(MqttClient client) {
        this.client = client;
    }
}
