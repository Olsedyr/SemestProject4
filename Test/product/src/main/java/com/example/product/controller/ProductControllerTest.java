package com.example.product.controller;

import com.example.product.dto.ProductDTO;
import com.example.product.model.Product;
import com.example.product.model.Recipe;
import com.example.product.service.ProductService;
import com.example.product.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {

    @Mock
    private ProductService productServiceMock;

    @Mock
    private RecipeService recipeServiceMock;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void testAddProduct() throws Exception {
        String name = "TestProduct";
        String description = "Test Description";
        Map<String, Integer> componentQuantities = new HashMap<>();
        Map<String, String> componentDescriptions = new HashMap<>();
        componentQuantities.put("Component1", 1);
        componentDescriptions.put("Component1", "Component Description");

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(name);
        productDTO.setDescription(description);
        productDTO.setComponentQuantities(componentQuantities);
        productDTO.setComponentDescriptions(componentDescriptions);

        Recipe savedRecipe = new Recipe();
        when(recipeServiceMock.addRecipe(name, componentQuantities, componentDescriptions)).thenReturn(savedRecipe);

        Product savedProduct = new Product();
        when(productServiceMock.addProduct(name, description, savedRecipe)).thenReturn(savedProduct);

        mockMvc.perform(post("/api/product/add")
                        .content(asJsonString(productDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that the service methods are called with the correct arguments
        verify(recipeServiceMock, times(1)).addRecipe(name, componentQuantities, componentDescriptions);
        verify(productServiceMock, times(1)).addProduct(name, description, savedRecipe);
    }

    // Helper method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}