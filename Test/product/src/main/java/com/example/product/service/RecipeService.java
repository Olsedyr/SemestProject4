package com.example.product.service;

import com.example.product.model.Part;
import com.example.product.model.Recipe;
import com.example.product.model.RecipePart;
import com.example.product.service.RecipeService;
import com.example.product.repository.PartRepository;
import com.example.product.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private PartRepository partRepository;

    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recipeService = new RecipeService(recipeRepository, partRepository);
    }

    @Test
    public void testAddRecipe() {

        String productName = "Test Product";
        Map<String, Integer> partQuantities = new HashMap<>();
        partQuantities.put("Part1", 5);
        partQuantities.put("Part2", 10);

        // Mock part repository behavior
        when(partRepository.findByName("Part1")).thenReturn(Optional.empty());
        when(partRepository.findByName("Part2")).thenReturn(Optional.empty());

        // Invoke the method under test
        Recipe result = recipeService.addRecipe(productName, partQuantities, new HashMap<>());

        // Verify interactions and assertions
        verify(recipeRepository, times(1)).save(any(Recipe.class));
        verify(partRepository, times(2)).findByName(anyString());
        verify(partRepository, times(2)).save(any(Part.class));
        assertEquals(productName, result.getProductName());
        assertEquals(2, result.getRecipeParts().size());
    }
}