package com.example.product.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.product.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class BatchTest {

    @InjectMocks
    private Batch batch;

    private List<Product> productList;

    @BeforeEach
    public void setUp() {
        productList = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        productList.add(product1);
        productList.add(product2);
        batch.setProducts(productList);
    }

    @Test
    public void testSetProducts() {
        // Given
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        products.add(product1);
        products.add(product2);

        // When
        batch.setProducts(products);

        // Then
        assertEquals(products, batch.getProducts());
    }

    @Test
    public void testSetCreatedAt() {
        // Given
        LocalDateTime createdAt = LocalDateTime.of(2022, 5, 5, 12, 0);

        // When
        batch.setCreatedAt(createdAt);

        // Then
        assertEquals(createdAt, batch.getCreatedAt());
    }

    @Test
    public void testSetCompleted() {
        // Given
        boolean completed = true;

        // When
        batch.setCompleted(completed);

        // Then
        assertTrue(batch.isCompleted());
    }
}