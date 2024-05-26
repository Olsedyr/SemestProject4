package com.example.agv;


import com.example.agv.agvConnection.AgvStatus;
import com.example.agv.agvConnection.AgvConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class AgvControllerTest {

    @Mock
    private AgvConnection agvConnection;

    @InjectMocks
    private AgvController agvController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMoveToChargerProgram() throws Exception {
        // Arrange
        when(agvConnection.getAgvStatus()).thenReturn(new AgvStatus()); // Mocking the response

        // Act & Assert
        MockMvc mockMvc = null; // Initialize your MockMvc instance here
        mockMvc.perform(MockMvcRequestBuilders.put("/agv/program/move-to-charger"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}