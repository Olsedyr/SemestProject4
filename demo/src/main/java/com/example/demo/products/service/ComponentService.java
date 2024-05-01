package com.example.demo.products.service;

import com.example.demo.products.model.Component;
import com.example.demo.products.repository.ComponentRepository;
import org.springframework.stereotype.Service;

@Service
public class ComponentService {
    ComponentRepository componentRepository;

    public ComponentService(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    public void addNewComponent(String name, String description){
        Component component = new Component(name,description);
        componentRepository.save(component);
    }
    public void addNewComponent(String name){
        Component component = new Component(name);
        componentRepository.save(component);
    }
}
