package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.product.model.Batch;
import com.example.product.repository.BatchRepository;
import com.example.production.ProductionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = AgvToChargerTest.class)
class AgvToChargerTest {

    private AgvToCharger agvToCharger;

    @Mock
    private BatchRepository mockBatchRepository;

    @Mock
    private AgvConnection mockAgvConnection;

    @Mock
    private Batch mockBatch;

    @Mock
    private AgvStatus mockAgvStatus;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        agvToCharger = new AgvToCharger();
        agvToCharger.batchRepository = mockBatchRepository;

        // Mock AgvConnection singleton instance
        mockStatic(AgvConnection.class);
        when(AgvConnection.getInstance()).thenReturn(mockAgvConnection);
    }

    @Test
    void moveAgvToCharger_batterySufficient_shouldSkipCharging() {
        // Arrange
        int batteryPercentage = 50;
        when(mockAgvConnection.getAgvStatus()).thenReturn(mockAgvStatus);
        when(mockAgvStatus.getBattery()).thenReturn(60);

        // Act
        ProductionStatus result = agvToCharger.moveAgvToCharger(batteryPercentage, mockBatch);

        // Assert
        assertTrue(result.isCompletedWithoutError());
        verify(mockBatch, times(1)).appendToLog("Battery level is sufficient, skipping charging phase");
        verify(mockBatchRepository, times(1)).save(mockBatch);
        verifyNoMoreInteractions(mockAgvConnection);
    }

    @Test
    void moveAgvToCharger_batteryLow_shouldCharge() throws InterruptedException {
        // Arrange
        int batteryPercentage = 50;
        when(mockAgvConnection.getAgvStatus()).thenReturn(mockAgvStatus);
        when(mockAgvStatus.getBattery()).thenReturn(40, 40, 40, 40, 100);
        when(mockAgvStatus.getProgramName()).thenReturn("MoveToChargerOperation");
        when(mockAgvStatus.getState()).thenReturn(1);

        // Act
        ProductionStatus result = agvToCharger.moveAgvToCharger(batteryPercentage, mockBatch);

        // Assert
        assertTrue(result.isCompletedWithoutError());
        verify(mockAgvConnection, times(1)).setProgram(AgvPrograms.MoveToChargerOperation);
        verify(mockAgvConnection, times(1)).startProgram();
        verify(mockBatch, times(1)).appendToLog("Program started: Move AVG to Charging Station");
        verify(mockBatch, times(1)).appendToLog("Program finished: AGV is fully charged");
        verify(mockBatchRepository, times(2)).save(mockBatch);
    }

    @Test
    void moveAgvToCharger_threadInterrupted_shouldHandleGracefully() throws InterruptedException {
        // Arrange
        int batteryPercentage = 50;
        when(mockAgvConnection.getAgvStatus()).thenReturn(mockAgvStatus);
        when(mockAgvStatus.getBattery()).thenReturn(40);
        when(mockAgvStatus.getProgramName()).thenReturn("MoveToChargerOperation");
        when(mockAgvStatus.getState()).thenReturn(1);

        // Simulate an interruption after the first check
        doThrow(new InterruptedException()).when(Thread.class);
        Thread.sleep(anyLong());

        // Act
        ProductionStatus result = agvToCharger.moveAgvToCharger(batteryPercentage, mockBatch);

        // Assert
        assertFalse(result.isCompletedWithoutError());
        verify(mockAgvConnection, times(1)).setProgram(AgvPrograms.MoveToChargerOperation);
        verify(mockAgvConnection, times(1)).startProgram();
        verify(mockBatch, times(1)).appendToLog("Program started: Move AVG to Charging Station");
        verify(mockBatchRepository, times(1)).save(mockBatch);
    }
}