package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Data
@Table(name = "Inventory")

public class testDB {
    //placeholder data to test the configuration of the database
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productId;
    private String description;
    private int quantity;
    private LocalDateTime lastUpdated;
}
