package com.example.product.dto;

import java.util.Map;

public class ProductDTO {

    public ProductDTO() {
    }
    public ProductDTO(String name, String description, Map<String, Integer> componentQuantities, Map<String, String> componentDescriptions) {
        this.name = name;
        this.description = description;
        this.componentQuantities = componentQuantities;
        this.componentDescriptions = componentDescriptions;
    }
    private String name;
    private String description;
    private Map<String, Integer> componentQuantities;
    private Map<String, String> componentDescriptions;

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
