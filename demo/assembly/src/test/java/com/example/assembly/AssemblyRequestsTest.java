package com.example.assembly;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
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
        assemblyRequests.getStatus();
        verify(mqttClientMock, times(1)).subscribe(eq("emulator/status"), any(IMqttMessageListener.class));
    }

    @Test
    public void testGetHealthCheck() throws MqttException {
        assemblyRequests.getHealthCheck();
        verify(mqttClientMock, times(1)).subscribe(eq("emulator/checkhealth"), any(IMqttMessageListener.class));
    }

    @Test
    public void testExecuteProgram() throws MqttException {
        assemblyRequests.executeProgram(100);
        verify(mqttClientMock, times(1)).publish(eq("emulator/operation"), any(MqttMessage.class));
    }
}