package com.example.product.service;

import com.example.product.model.Part;
import com.example.product.model.Recipe;
import com.example.product.model.RecipePart;
import com.example.product.repository.PartRepository;
import com.example.product.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    RecipeRepository recipeRepository;
    PartRepository partRepository;

    public RecipeService(RecipeRepository recipeRepository, PartRepository partRepository) {
        this.recipeRepository = recipeRepository;
        this.partRepository = partRepository;
    }



    @Transactional
    public Recipe addRecipe(String productName, Map<String, Integer> partQuantities, Map<String, String> partDescriptions) {
        List<RecipePart> recipeParts = new ArrayList<>();

        Recipe recipe = new Recipe();
        recipe.setProductName(productName);
        recipe = recipeRepository.save(recipe);

        // Iterate over each part
        for (Map.Entry<String, Integer> entry : partQuantities.entrySet()) {
            String partName = entry.getKey();
            int quantity = entry.getValue();
            String partDescription = partDescriptions.get(partName);

            // Check if the part exists in the database
            Optional<Part> optionalPart = partRepository.findByName(partName);

            // If the part doesn't exist, create and save it
            Part part;
            if (optionalPart.isEmpty()) {
                part = new Part(partName);
                if (partDescription != null) {
                    part.setDescription(partDescription);
                }
                part = partRepository.save(part);
            } else {
                part = optionalPart.get();
                if (partDescription != null) {
                    part.setDescription(partDescription);
                }
            }

            // Create a RecipePart for this part with the specified quantity
            RecipePart recipePart = new RecipePart();
            recipePart.setPart(part);
            recipePart.setQuantity(quantity);
            recipePart.setRecipe(recipe);
            recipeParts.add(recipePart);
        }

        // Set the recipe parts for the recipe
        recipe.setRecipeParts(recipeParts);
        return recipe;
    }



}
