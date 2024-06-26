package com.example.product.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public int getTrayId() {
        return trayId;
    }

    public void setTrayId(int trayId) {
        this.trayId = trayId;
    }

    private int trayId;

    @OneToMany(mappedBy = "part", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<RecipePart> recipeParts;

    @Column(nullable = true,length = 1_000)
    private String description;
    public Part() {
    }

    public Part(String name) {
        this.name = name;
    }

    public Part(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public List<RecipePart> getRecipeParts() {
        return recipeParts;
    }

    public void setRecipeParts(List<RecipePart> recipeParts) {
        this.recipeParts = recipeParts;
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
