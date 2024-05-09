package com.example.product.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class ProductTest {

    @InjectMocks
    private Product product;

    private Recipe recipe;

    @BeforeEach
    public void setUp() {
        recipe = new Recipe();
        product.setRecipe(recipe);
    }

    @Test
    public void testSetName() {
        // Given
        String name = "New Product Name";

        // When
        product.setName(name);

        // Then
        assertEquals(name, product.getName());
    }

    @Test
    public void testSetRecipe() {
        // Given
        Recipe newRecipe = new Recipe();

        // When
        product.setRecipe(newRecipe);

        // Then
        assertEquals(newRecipe, product.getRecipe());
    }

    @Test
    public void testSetDescription() {
        // Given
        String description = "New Product Description";

        // When
        product.setDescription(description);

        // Then
        assertEquals(description, product.getDescription());
    }

    @Test
    public void testToString() {
        // When
        String productString = product.toString();

        // Then
        assertEquals("Product{id=null, name='null', recipe=" + recipe.toString() + '}', productString);
    }
}
