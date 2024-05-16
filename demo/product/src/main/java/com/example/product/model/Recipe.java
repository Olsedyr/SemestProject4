package com.example.product.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = true)
    @JsonBackReference
    private Product product;
    private String productName;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference // Handles serialization on the forward part of the reference
    private List<RecipePart> recipeParts;

    public Recipe(String productName, List<RecipePart> recipeParts) {
        this.productName = productName;
        this.recipeParts = recipeParts;
    }

    public Recipe() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<RecipePart> getRecipeParts() {
        return recipeParts;
    }

    public void setRecipeParts(List<RecipePart> recipeParts) {
        this.recipeParts = recipeParts;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}

