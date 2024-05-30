package com.example.product.controller;

import com.example.product.dto.RecipeDTO;
import com.example.product.model.Recipe;
import com.example.product.service.RecipeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @InjectMocks
    private RecipeController recipeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    public void testAddRecipe() throws Exception {
        RecipeDTO recipeDTO = new RecipeDTO();
        recipeDTO.setProductName("TestProduct");

        Map<String, Integer> componentQuantities = new HashMap<>();
        componentQuantities.put("Component1", 10);
        componentQuantities.put("Component2", 20);
        recipeDTO.setComponentQuantities(componentQuantities);

        Map<String, String> componentDescriptions = new HashMap<>();
        componentDescriptions.put("Component1", "Description1");
        componentDescriptions.put("Component2", "Description2");
        recipeDTO.setComponentDescriptions(componentDescriptions);

        Recipe recipe = new Recipe(); // Initialize with appropriate constructor or setters
        when(recipeService.addRecipe(any(String.class), any(Map.class), any(Map.class))).thenReturn(recipe);

        try {
            mockMvc.perform(post("/api/recipe/add")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(recipeDTO)))
                    .andExpect(status().isOk());
            verify(recipeService).addRecipe(recipeDTO.getProductName(), recipeDTO.getComponentQuantities(), recipeDTO.getComponentDescriptions());
        } catch (NestedServletException e) {
            // If an exception occurs during the test, fail the test and print the stack trace
            e.printStackTrace();
            throw e;
        }
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