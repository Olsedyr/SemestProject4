package com.example.demo.products.service;

import com.example.demo.products.model.Component;
import com.example.demo.products.model.Product;
import com.example.demo.products.model.Recipe;
import com.example.demo.products.model.RecipeComponent;
import com.example.demo.products.repository.ComponentRepository;
import com.example.demo.products.repository.RecipeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RecipeService {

    RecipeRepository recipeRepository;
    ComponentRepository componentRepository;

    public RecipeService(RecipeRepository recipeRepository, ComponentRepository componentRepository) {
        this.recipeRepository = recipeRepository;
        this.componentRepository = componentRepository;
    }



//    @Transactional
//    public Recipe addRecipe(String productName, Map<String, Integer> componentQuantities) {
//        List<RecipeComponent> recipeComponents = new ArrayList<>();
//
//        Recipe recipe = new Recipe();
//        recipe.setProductName(productName);
//        recipe = recipeRepository.save(recipe);
//
//        // Iterate over each component
//        for (Map.Entry<String, Integer> entry : componentQuantities.entrySet()) {
//            String componentName = entry.getKey();
//            int quantity = entry.getValue();
//
//            // Check if the component exists in the database
//            Optional<Component> optionalComponent = componentRepository.findByName(componentName);
//
//            // If the component doesn't exist, create and save it
//            Component component;
//            if (optionalComponent.isEmpty()) {
//                component = new Component(componentName);
//                component = componentRepository.save(component);
//            } else {
//                component = optionalComponent.get();
//            }
//
//            // Create a RecipeComponent for this component with the specified quantity
//            RecipeComponent recipeComponent = new RecipeComponent();
//            recipeComponent.setComponent(component);
//            recipeComponent.setQuantity(quantity);
//            recipeComponent.setRecipe(recipe);
//            recipeComponents.add(recipeComponent);
//        }
//
//        // Set the recipe components for the recipe
//        recipe.setRecipeComponents(recipeComponents);
//        return recipe;
//    }

    @Transactional
    public Recipe addRecipe(String productName, Map<String, Integer> componentQuantities, Map<String, String> componentDescriptions) {
        List<RecipeComponent> recipeComponents = new ArrayList<>();

        Recipe recipe = new Recipe();
        recipe.setProductName(productName);
        recipe = recipeRepository.save(recipe);

        // Iterate over each component
        for (Map.Entry<String, Integer> entry : componentQuantities.entrySet()) {
            String componentName = entry.getKey();
            int quantity = entry.getValue();
            String componentDescription = componentDescriptions.get(componentName);

            // Check if the component exists in the database
            Optional<Component> optionalComponent = componentRepository.findByName(componentName);

            // If the component doesn't exist, create and save it
            Component component;
            if (optionalComponent.isEmpty()) {
                component = new Component(componentName);
                if (componentDescription != null) {
                    component.setDescription(componentDescription);
                }
                component = componentRepository.save(component);
            } else {
                component = optionalComponent.get();
                if (componentDescription != null) {
                    component.setDescription(componentDescription);
                }
            }

            // Create a RecipeComponent for this component with the specified quantity
            RecipeComponent recipeComponent = new RecipeComponent();
            recipeComponent.setComponent(component);
            recipeComponent.setQuantity(quantity);
            recipeComponent.setRecipe(recipe);
            recipeComponents.add(recipeComponent);
        }

        // Set the recipe components for the recipe
        recipe.setRecipeComponents(recipeComponents);
        return recipe;
    }



}
