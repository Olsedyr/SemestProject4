package com.example.product.service;

import com.example.product.model.Part;
import com.example.product.model.Recipe;
import com.example.product.model.RecipePart;
import com.example.product.repository.PartRepository;
import com.example.product.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;

public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepositoryMock;

    @Mock
    private PartRepository partRepositoryMock;

    @InjectMocks
    private RecipeService recipeService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddRecipe() {
        String productName = "TestProduct";
        Map<String, Integer> partQuantities = new HashMap<>();
        partQuantities.put("Part1", 5);
        partQuantities.put("Part2", 10);

        Map<String, String> partDescriptions = new HashMap<>();
        partDescriptions.put("Part1", "Description for Part1");
        partDescriptions.put("Part2", "Description for Part2");

        Part part1 = new Part("Part1");
        Part part2 = new Part("Part2");

        when(partRepositoryMock.findByName("Part1")).thenReturn(Optional.of(part1));
        when(partRepositoryMock.findByName("Part2")).thenReturn(Optional.of(part2));

        Recipe savedRecipe = new Recipe();


        when(recipeRepositoryMock.save(any(Recipe.class))).thenReturn(savedRecipe);

        Recipe addedRecipe = recipeService.addRecipe(productName, partQuantities, partDescriptions);

        verify(recipeRepositoryMock, times(1)).save(any(Recipe.class));
        verify(partRepositoryMock, times(2)).findByName(anyString());

        // Add your assertions here based on the behavior of the service method
    }
}