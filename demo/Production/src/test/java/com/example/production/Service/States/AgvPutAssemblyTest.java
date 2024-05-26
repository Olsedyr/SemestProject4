package com.example.production.Service.States;

import com.example.product.model.Part;
import com.example.product.model.Product;
import com.example.product.model.Recipe;
import com.example.product.model.RecipePart;
import com.example.product.repository.BatchRepository;
import com.example.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class AgvPutAssemblyTest {

    private AgvPutAssembly agvPutAssembly;

    @Mock
    private ProductRepository mockProductRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        agvPutAssembly = new AgvPutAssembly();
        agvPutAssembly.batchRepository = mock(BatchRepository.class);
    }

    @Test
    void getPartList_shouldReturnListOfParts() {
        // Arrange
        Product product = new Product();
        Recipe recipe = new Recipe();
        RecipePart recipePart1 = new RecipePart();
        recipePart1.setPart(new Part("Part1", "1"));
        RecipePart recipePart2 = new RecipePart();
        recipePart2.setPart(new Part("Part2", "2"));
        List<RecipePart> recipeParts = new ArrayList<>();
        recipeParts.add(recipePart1);
        recipeParts.add(recipePart2);
        recipe.setRecipeParts(recipeParts);
        product.setRecipe(recipe);
        when(mockProductRepository.findByName(anyString())).thenReturn(product);

        // Act
        List<Part> parts = agvPutAssembly.getPartList(product);

        // Assert
        verify(mockProductRepository, times(1)).findByName(anyString());
        assert parts.size() == 2;
        assert parts.get(0).getName().equals("Part1");
        assert parts.get(1).getName().equals("Part2");
    }

    // You can add more test methods for other functionalities of AgvPutAssembly if needed
}