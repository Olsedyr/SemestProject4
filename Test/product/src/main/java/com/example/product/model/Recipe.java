package com.example.product.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class RecipeTest {

    @InjectMocks
    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        recipe = new Recipe();
    }

    @Test
    public void testSetProduct() {
        // Given
        Product product = new Product();

        // When
        recipe.setProduct(product);

        // Then
        assertEquals(product, recipe.getProduct());
    }

    @Test
    public void testSetProductName() {
        // Given
        String productName = "Test Product Name";

        // When
        recipe.setProductName(productName);

        // Then
        assertEquals(productName, recipe.getProductName());
    }

    @Test
    public void testSetRecipeParts() {
        // Given
        List<RecipePart> recipeParts = new ArrayList<>();

        // When
        recipe.setRecipeParts(recipeParts);

        // Then
        assertEquals(recipeParts, recipe.getRecipeParts());
    }
}
