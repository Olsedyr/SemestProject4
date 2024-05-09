package com.example.product.controller;

import com.example.product.dto.RecipeDTO;
import com.example.product.model.Recipe;
import com.example.product.service.RecipeService;
import com.example.product.service.RecipeServiceTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "api/recipe")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeControllerTest {

    private final RecipeService recipeService;

    @Autowired
    public RecipeControllerTest(RecipeService recipeService) {
        this.recipeService = recipeService;
    }



//    @PostMapping("/add")
//    public Recipe addRecipe(@RequestBody Map<String, Object> requestBody) {
//        String productName = (String) requestBody.get("productName");
//        Map<String, Integer> componentQuantities = (Map<String, Integer>) requestBody.get("componentQuantities");
//        return recipeService.addRecipe(productName, componentQuantities);
//    }

    @PostMapping("/add")
    public Recipe addRecipe(@RequestBody RecipeDTO recipeDTO) {
        String productName = recipeDTO.getProductName();
        Map<String, Integer> componentQuantities = recipeDTO.getComponentQuantities();
        Map<String, String> componentDescriptions = recipeDTO.getComponentDescriptions();
        return recipeService.addRecipe(productName, componentQuantities, componentDescriptions);
    }


}
