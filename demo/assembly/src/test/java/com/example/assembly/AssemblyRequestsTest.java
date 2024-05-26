package com.example.assembly;

import org.eclipse.paho.client.mqttv3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

public class AssemblyRequestsTest {

    @Mock
    private AssemblyConnection assemblyConnectionMock;

    @Mock
    private MqttClient mqttClientMock;

    private AssemblyRequests assemblyRequests;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(assemblyConnectionMock.getClient()).thenReturn(mqttClientMock);
        assemblyRequests = new AssemblyRequests();
        assemblyRequests.connection = assemblyConnectionMock;
    }

    @Test
    public void testGetStatus() throws MqttException {
        // Mock the behavior of MqttClient's subscribe method
        doNothing().when(mqttClientMock).subscribe(anyString(), any(IMqttMessageListener.class));

        // Call the method under test
        assemblyRequests.getStatus();

        // Verify that the subscribe method of mqttClientMock was called with the expected arguments
        verify(mqttClientMock, times(1)).subscribe(eq("emulator/status"), any(IMqttMessageListener.class));
    }

    @Test
    public void testGetHealthCheck() throws MqttException {
        // Mock the behavior of MqttClient's subscribe method
        doNothing().when(mqttClientMock).subscribe(anyString(), any(IMqttMessageListener.class));

        // Call the method under test
        assemblyRequests.getHealthCheck();

        // Verify that the subscribe method of mqttClientMock was called with the expected arguments
        verify(mqttClientMock, times(1)).subscribe(eq("emulator/checkhealth"), any(IMqttMessageListener.class));
    }

    @Test
    public void testExecuteProgram() throws MqttException {
        // Mock the behavior of MqttClient's publish method
        doNothing().when(mqttClientMock).publish(anyString(), any(MqttMessage.class));

        // Call the method under test
        assemblyRequests.executeProgram(100);

        // Verify that the publish method of mqttClientMock was called with the expected arguments
        verify(mqttClientMock, times(1)).publish(eq("emulator/operation"), any(MqttMessage.class));
    }
}