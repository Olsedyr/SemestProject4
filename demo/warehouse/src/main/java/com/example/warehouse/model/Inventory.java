package com.example.warehouse.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int trayID;
    private String name;

    private Timestamp timestamp;

    public Inventory() {
    }

    public Inventory(int trayID, String name) {
        this.trayID = trayID;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTrayID() {
        return trayID;
    }

    public void setTrayID(int trayID) {
        this.trayID = trayID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
