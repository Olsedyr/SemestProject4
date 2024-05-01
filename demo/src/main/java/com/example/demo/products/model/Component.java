package com.example.demo.products.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Component {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "component", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeComponent> recipeComponents;

    @Column(nullable = true,length = 1_000)
    private String description;
    public Component() {
    }

    public Component(String name) {
        this.name = name;
    }

    public Component(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public List<RecipeComponent> getRecipeComponents() {
        return recipeComponents;
    }

    public void setRecipeComponents(List<RecipeComponent> recipeComponents) {
        this.recipeComponents = recipeComponents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
