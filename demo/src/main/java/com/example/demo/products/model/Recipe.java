package com.example.demo.products.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = true)
    private Product product;
    private String productName;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeComponent> recipeComponents;


    public Recipe(String productName, List<RecipeComponent> recipeComponents) {
        this.productName = productName;
        this.recipeComponents = recipeComponents;
    }

    public Recipe() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<RecipeComponent> getRecipeComponents() {
        return recipeComponents;
    }

    public void setRecipeComponents(List<RecipeComponent> recipeComponents) {
        this.recipeComponents = recipeComponents;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

