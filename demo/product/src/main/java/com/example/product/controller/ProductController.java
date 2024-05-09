package com.example.product.controller;

import com.example.product.dto.ProductDTO;
import com.example.product.model.Product;
import com.example.product.model.Recipe;
import com.example.product.service.ProductService;
import com.example.product.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

    private final ProductService productService;
    private final RecipeService recipeService;

    @Autowired
    public ProductController(ProductService productService, RecipeService recipeService) {
        this.productService = productService;
        this.recipeService = recipeService;
    }



    @PostMapping("/add")
    public Product addProduct(@RequestBody ProductDTO productDTO) {
        // Map DTO fields to Product entity
        String name = productDTO.getName();
        String description = productDTO.getDescription();
        Map<String, Integer> componentQuantities = productDTO.getComponentQuantities();
        Map<String, String> componentDescriptions = productDTO.getComponentDescriptions();

        // Save the recipe first
        Recipe savedRecipe = recipeService.addRecipe(name, componentQuantities, componentDescriptions);

        // Add product using ProductService
        return productService.addProduct(name, description, savedRecipe);
    }


}
