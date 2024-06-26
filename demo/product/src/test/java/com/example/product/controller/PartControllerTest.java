package com.example.product.controller;

import com.example.product.dto.PartDTO;
import com.example.product.service.PartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ContextConfiguration(classes = {PartController.class})

public class PartControllerTest {

    @Mock
    private PartService partServiceMock;

    @InjectMocks
    private PartController partController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(partController).build();
    }

    @Test
    public void testAddNewBatch() throws Exception {
        String name = "TestPart";
        mockMvc.perform(post("/api/part/add")
                        .content(name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the service method is called with the correct argument
        verify(partServiceMock).addNewPart(name);
    }

    @Test
    public void testAddNewBatchWithDescription() throws Exception {
        String name = "TestPart";
        String description = "Test Description";

        // Create a PartDTO object
        PartDTO partDTO = new PartDTO();
        partDTO.setName(name);
        partDTO.setDescription(description);

        mockMvc.perform(post("/api/part/add-with-description")
                        .content(asJsonString(partDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the service method is called with the correct arguments
        verify(partServiceMock).addNewPart(name, description);
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}