package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.model.Recipe;
import com.example.product.repository.ProductRepository;
import com.example.product.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepositoryMock;

    @Mock
    private RecipeRepository recipeRepositoryMock;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddProduct() {
        String productName = "TestProduct";
        String productDescription = "Test Description";
        Product product = new Product();
        product.setName(productName);
        product.setDescription(productDescription);

        String recipeName = "TestRecipe";
        Recipe recipe = new Recipe();
        recipe.setProductName(recipeName);

        when(recipeRepositoryMock.save(recipe)).thenReturn(recipe);
        when(productRepositoryMock.save(product)).thenReturn(product);

        productService.addProduct(productName, productDescription, recipe);

        // Verify that the save method is called with the correct arguments
        verify(recipeRepositoryMock).save(recipe);
        verify(productRepositoryMock).save(product);
    }
}