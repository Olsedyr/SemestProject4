package com.example.demo.products.dto;

import java.util.Map;

public class RecipeDTO {
    private String productName;
    private Map<String, Integer> componentQuantities;
    private Map<String, String> componentDescriptions;

    // Getters and setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Map<String, Integer> getComponentQuantities() {
        return componentQuantities;
    }

    public void setComponentQuantities(Map<String, Integer> componentQuantities) {
        this.componentQuantities = componentQuantities;
    }

    public Map<String, String> getComponentDescriptions() {
        return componentDescriptions;
    }

    public void setComponentDescriptions(Map<String, String> componentDescriptions) {
        this.componentDescriptions = componentDescriptions;
    }
}
