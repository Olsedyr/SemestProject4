package com.example.product.service;

import com.example.product.model.Product;
import com.example.product.model.Recipe;
import com.example.product.repository.ProductRepository;
import com.example.product.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    public ProductRepository productRepository;
    @Autowired
    public RecipeRepository recipeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, RecipeRepository recipeRepository) {
        this.productRepository = productRepository;
        this.recipeRepository = recipeRepository;
    }
    public ProductService(){

    }

    @Transactional
    public Product addProduct(String name, String description, Recipe recipe) {

        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setRecipe(recipe);
        recipe.setProduct(product);
        recipeRepository.save(recipe);

        // Save the product to the database
        return productRepository.save(product);
    }

}

