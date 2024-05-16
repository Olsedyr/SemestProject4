package com.example.product.controller;

import com.example.product.dto.RecipeDTO;
import com.example.product.model.Recipe;
import com.example.product.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/recipe")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {

    private final RecipeService recipeService;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }




    @PostMapping("/add")
    public Recipe addRecipe(@RequestBody RecipeDTO recipeDTO) {
        String productName = recipeDTO.getProductName();
        Map<String, Integer> componentQuantities = recipeDTO.getComponentQuantities();
        Map<String, String> componentDescriptions = recipeDTO.getComponentDescriptions();
        return recipeService.addRecipe(productName, componentQuantities, componentDescriptions);
    }


}
