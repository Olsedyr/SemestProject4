package com.example.demo.products.service;

import com.example.demo.products.model.Product;
import com.example.demo.products.model.Recipe;
import com.example.demo.products.repository.ProductRepository;
import com.example.demo.products.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final RecipeRepository recipeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, RecipeRepository recipeRepository) {
        this.productRepository = productRepository;
        this.recipeRepository = recipeRepository;
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
