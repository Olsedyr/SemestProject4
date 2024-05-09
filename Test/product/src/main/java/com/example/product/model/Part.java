package com.example.product.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PartTest {

    @InjectMocks
    private Part part;

    private List<RecipePart> recipeParts;

    @BeforeEach
    public void setUp() {
        recipeParts = new ArrayList<>();
        RecipePart recipePart1 = new RecipePart();
        RecipePart recipePart2 = new RecipePart();
        recipeParts.add(recipePart1);
        recipeParts.add(recipePart2);
        part.setRecipeParts(recipeParts);
    }

    @Test
    public void testSetRecipeParts() {
        // Given
        List<RecipePart> newRecipeParts = new ArrayList<>();
        RecipePart recipePart1 = new RecipePart();
        RecipePart recipePart2 = new RecipePart();
        newRecipeParts.add(recipePart1);
        newRecipeParts.add(recipePart2);

        // When
        part.setRecipeParts(newRecipeParts);

        // Then
        assertEquals(newRecipeParts, part.getRecipeParts());
    }

    @Test
    public void testSetName() {
        // Given
        String name = "New Part Name";

        // When
        part.setName(name);

        // Then
        assertEquals(name, part.getName());
    }

    @Test
    public void testSetDescription() {
        // Given
        String description = "New Part Description";

        // When
        part.setDescription(description);

        // Then
        assertEquals(description, part.getDescription());
    }
}