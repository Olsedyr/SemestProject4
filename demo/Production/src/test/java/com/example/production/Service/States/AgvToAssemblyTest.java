package com.example.production.Service.States;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import com.example.product.repository.BatchRepository;
import com.example.production.ProductionStatus;
import com.example.production.Service.States.AgvToAssembly;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AgvToAssemblyTest {

    @Mock
    private BatchRepository batchRepository;

    @Mock
    private AgvConnection agvConnection;

    @InjectMocks
    private AgvToAssembly agvToAssembly;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        agvToAssembly = new AgvToAssembly();
        agvToAssembly.batchRepository = batchRepository;  // Inject the mock repository
    }

    @Test
    public void testMoveAgvToAssemblySuccess() {
        // Arrange
        AgvStatus mockStatus = mock(AgvStatus.class);
        when(agvConnection.getAgvStatus()).thenReturn(mockStatus);
        when(mockStatus.getProgramName()).thenReturn("MoveToAssemblyOperation");
        when(mockStatus.getState()).thenReturn(1);

        // Act
        ProductionStatus result = agvToAssembly.moveAgvToAssembly();

        // Assert
        verify(agvConnection).setProgram(AgvPrograms.MoveToAssemblyOperation);
        verify(agvConnection).startProgram();
        assertTrue(result.isCompletedWithoutError());
        assertTrue(result.getLog().contains("Program started: Move AVG to Assembly Station"));
        assertTrue(result.getLog().contains("Program finished: AGV successfully moved to assembly Station"));
    }

    @Test
    public void testMoveAgvToAssemblyFailure() {
        // Arrange
        AgvStatus mockStatus = mock(AgvStatus.class);
        when(agvConnection.getAgvStatus()).thenReturn(mockStatus);
        when(mockStatus.getProgramName()).thenReturn("SomeOtherOperation");
        when(mockStatus.getState()).thenReturn(0);

        // Act
        ProductionStatus result = agvToAssembly.moveAgvToAssembly();

        // Assert
        verify(agvConnection).setProgram(AgvPrograms.MoveToAssemblyOperation);
        verify(agvConnection).startProgram();
        assertFalse(result.isCompletedWithoutError());
        assertTrue(result.getLog().contains("Program started: Move AVG to Assembly Station"));
        assertFalse(result.getLog().contains("Program finished: AGV successfully moved to assembly Station"));
    }
}