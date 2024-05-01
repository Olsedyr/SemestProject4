package com.example.demo.products.controller;

import com.example.demo.products.dto.ProductDTO;
import com.example.demo.products.model.Product;
import com.example.demo.products.model.Recipe;
import com.example.demo.products.model.RecipeComponent;
import com.example.demo.products.service.ProductService;
import com.example.demo.products.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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


//    @PostMapping("/add")
//    public Product addProduct(@RequestBody ProductDTO productDTO) {
//        // Map DTO fields to Product entity
//        String name = productDTO.getName();
//        String description = productDTO.getDescription();
//        Recipe recipe = productDTO.getRecipe();
//
//        // Extract product name and component quantities from the recipe
//        String productName = recipe.getProductName();
//        Map<String, Integer> componentQuantities = new HashMap<>();
//        for (RecipeComponent component : recipe.getRecipeComponents()) {
//            componentQuantities.put(component.getComponent().getName(), component.getQuantity());
//        }
//
//        // Save the recipe first
//        Recipe savedRecipe = recipeService.addRecipe(productName, componentQuantities);
//
//        // Add product using ProductService
//        return productService.addProduct(name, description, savedRecipe);
//    }

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
