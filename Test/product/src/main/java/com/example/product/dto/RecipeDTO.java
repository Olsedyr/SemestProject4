package com.example.product.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RecipeDTOTest {

    @InjectMocks
    private RecipeDTO recipeDTO;

    @Test
    public void testSetProductName() {
        // Given
        String productName = "Test Product";

        // When
        recipeDTO.setProductName(productName);

        // Then
        assertEquals(productName, recipeDTO.getProductName());
    }

    @Test
    public void testSetComponentQuantities() {
        // Given
        Map<String, Integer> componentQuantities = new HashMap<>();
        componentQuantities.put("Component1", 5);
        componentQuantities.put("Component2", 10);

        // When
        recipeDTO.setComponentQuantities(componentQuantities);

        // Then
        assertEquals(componentQuantities, recipeDTO.getComponentQuantities());
    }

    @Test
    public void testSetComponentDescriptions() {
        // Given
        Map<String, String> componentDescriptions = new HashMap<>();
        componentDescriptions.put("Component1", "Description1");
        componentDescriptions.put("Component2", "Description2");

        // When
        recipeDTO.setComponentDescriptions(componentDescriptions);

        // Then
        assertEquals(componentDescriptions, recipeDTO.getComponentDescriptions());
    }
}