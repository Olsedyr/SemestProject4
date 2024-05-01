package com.example.demo.products.controller;

import com.example.demo.products.dto.ComponentDTO;
import com.example.demo.products.service.ComponentService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/component")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from frontend
public class ComponentController {
    ComponentService componentService;

    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

    @PostMapping("/add")
    public void addNewBatch(@RequestBody String name) {
        componentService.addNewComponent(name);
    }


    @PostMapping("/add-with-description")
    public void addNewBatch(@RequestBody ComponentDTO requestBody) {
        String name = requestBody.getName();
        String description = requestBody.getDescription();
        componentService.addNewComponent(name, description);
    }
}
