package com.example.product.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Batch {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany
//    private List<Product> products;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "batch_products",
            joinColumns = @JoinColumn(name = "batch_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();

    private LocalDateTime createdAt;
    private boolean completed;
    @Column(columnDefinition = "TEXT")
    private String log;

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }


    public Batch() {
        this.createdAt=LocalDateTime.now();
        this.log = "";
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void appendToLog(String message) {
        LocalDateTime now = LocalDateTime.now();
        this.log += now.toString() + " - " + message + "\n";
    }
    public void appendToLogNoTimeStamp(String message) {
        this.log += message + "\n";
    }
}
