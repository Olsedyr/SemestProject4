package com.example.product.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductDTOTest {

    @InjectMocks
    private ProductDTO productDTO;

    @Test
    public void testSetName() {
        // Given
        String name = "Test Product";

        // When
        productDTO.setName(name);

        // Then
        assertEquals(name, productDTO.getName());
    }

    @Test
    public void testSetDescription() {
        // Given
        String description = "Test Description";

        // When
        productDTO.setDescription(description);

        // Then
        assertEquals(description, productDTO.getDescription());
    }

    @Test
    public void testSetComponentQuantities() {
        // Given
        Map<String, Integer> componentQuantities = new HashMap<>();
        componentQuantities.put("Component1", 5);
        componentQuantities.put("Component2", 10);

        // When
        productDTO.setComponentQuantities(componentQuantities);

        // Then
        assertEquals(componentQuantities, productDTO.getComponentQuantities());
    }

    @Test
    public void testSetComponentDescriptions() {
        // Given
        Map<String, String> componentDescriptions = new HashMap<>();
        componentDescriptions.put("Component1", "Description1");
        componentDescriptions.put("Component2", "Description2");

        // When
        productDTO.setComponentDescriptions(componentDescriptions);

        // Then
        assertEquals(componentDescriptions, productDTO.getComponentDescriptions());
    }
}