package com.example.product.service;

import com.example.product.model.Part;
import com.example.product.repository.PartRepository;
import org.springframework.stereotype.Service;

@Service
public class PartService {
    PartRepository partRepository;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
    }

    public void addNewPart(String name, String description){
        Part part = new Part(name,description);
        partRepository.save(part);
    }
    public void addNewPart(String name){
        Part part = new Part(name);
        partRepository.save(part);
    }
}
