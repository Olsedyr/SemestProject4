package com.example.assembly;

import com.example.assembly.AssemblyConnection;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AssemblyConnectionTest {

    @Mock
    private MqttClient mqttClientMock;

    private AssemblyConnection assemblyConnection;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        assemblyConnection = AssemblyConnection.getConnection();
        assemblyConnection.setClient(mqttClientMock);
    }

    @Test
    public void testGetConnection() {
        assertEquals(assemblyConnection, AssemblyConnection.getConnection());
    }

    @Test
    public void testGetBroker() {
        assertEquals("tcp://localhost:1883", assemblyConnection.getBroker());
    }

    @Test
    public void testGetClientId() {
        assertEquals("client-id", assemblyConnection.getClientId());
    }

    @Test
    public void testGetTopics() {
        assertEquals(3, assemblyConnection.getTopics().length);
    }


    @Test
    public void testSetClientId() {
        String newClientId = "new-client-id";
        assemblyConnection.setClientId(newClientId);
        assertEquals(newClientId, assemblyConnection.getClientId());
    }

    @Test
    public void testSetTopics() {
        String[] newTopics = {"topic1", "topic2"};
        assemblyConnection.setTopics(newTopics);
        assertEquals(newTopics, assemblyConnection.getTopics());
    }

    @Test
    public void testClientConnect() throws MqttException {
        assemblyConnection.getClient().connect();
        verify(mqttClientMock, times(1)).connect();
    }

    @Test
    public void testClientConnectException() throws MqttException {
        doThrow(new MqttException(MqttException.REASON_CODE_CLIENT_CONNECTED)).when(mqttClientMock).connect();
        assemblyConnection.getClient().connect();
        // Ensure the error message is printed
    }
}