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

@SpringBootTest(classes = AgvToWarehouseTest.class)
class AgvToWarehouseTest {

    private AgvToWarehouse agvToWarehouse;

    @Mock
    private BatchRepository mockBatchRepository;

    @Mock
    private AgvConnection mockAgvConnection;

    @Mock
    private AgvStatus mockAgvStatus;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        agvToWarehouse = new AgvToWarehouse();
        agvToWarehouse.batchRepository = mockBatchRepository;

        // Mock AgvConnection singleton instance
        mockStatic(AgvConnection.class);
        when(AgvConnection.getInstance()).thenReturn(mockAgvConnection);
    }

    @Test
    void moveAgvToWarehouse_shouldMoveSuccessfully() throws InterruptedException {
        // Arrange
        when(mockAgvConnection.getAgvStatus()).thenReturn(mockAgvStatus);
        when(mockAgvStatus.getProgramName()).thenReturn("MoveToStorageOperation");
        when(mockAgvStatus.getState()).thenReturn(0).thenReturn(1); // first call 0, second call 1

        // Act
        ProductionStatus result = agvToWarehouse.moveAgvToWarehouse();

        // Assert
        assertTrue(result.isCompletedWithoutError());
        verify(mockAgvConnection, times(1)).setProgram(AgvPrograms.MoveToStorageOperation);
        verify(mockAgvConnection, times(1)).startProgram();
        verify(mockAgvStatus, atLeast(1)).getProgramName();
        verify(mockAgvStatus, atLeast(1)).getState();
    }

    @Test
    void moveAgvToWarehouse_shouldFailToMoveWithinAttempts() throws InterruptedException {
        // Arrange
        when(mockAgvConnection.getAgvStatus()).thenReturn(mockAgvStatus);
        when(mockAgvStatus.getProgramName()).thenReturn("MoveToStorageOperation");
        when(mockAgvStatus.getState()).thenReturn(0); // Always return state 0

        // Act
        ProductionStatus result = agvToWarehouse.moveAgvToWarehouse();

        // Assert
        assertFalse(result.isCompletedWithoutError());
        verify(mockAgvConnection, times(1)).setProgram(AgvPrograms.MoveToStorageOperation);
        verify(mockAgvConnection, times(1)).startProgram();
        verify(mockAgvStatus, atLeast(20)).getProgramName();
        verify(mockAgvStatus, atLeast(20)).getState();
    }

    @Test
    void moveAgvToWarehouse_shouldHandleThreadInterruptionGracefully() throws InterruptedException {
        // Arrange
        when(mockAgvConnection.getAgvStatus()).thenReturn(mockAgvStatus);
        when(mockAgvStatus.getProgramName()).thenReturn("MoveToStorageOperation");
        when(mockAgvStatus.getState()).thenReturn(0); // Always return state 0

        // Simulate an interruption after the first check
        doThrow(new InterruptedException()).when(Thread.class);
        Thread.sleep(anyLong());

        // Act
        ProductionStatus result = agvToWarehouse.moveAgvToWarehouse();

        // Assert
        assertFalse(result.isCompletedWithoutError());
        verify(mockAgvConnection, times(1)).setProgram(AgvPrograms.MoveToStorageOperation);
        verify(mockAgvConnection, times(1)).startProgram();
        verify(mockAgvStatus, atLeast(1)).getProgramName();
        verify(mockAgvStatus, atLeast(1)).getState();
    }
}