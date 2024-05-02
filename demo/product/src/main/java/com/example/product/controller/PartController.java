package com.example.product.controller;

import com.example.product.dto.PartDTO;
import com.example.product.service.PartService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "api/part")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from frontend
public class PartController {
    PartService partService;

    public PartController(PartService partService) {
        this.partService = partService;
    }

    @PostMapping("/add")
    public void addNewBatch(@RequestBody String name) {
        partService.addNewPart(name);
    }


    @PostMapping("/add-with-description")
    public void addNewBatch(@RequestBody PartDTO requestBody) {
        String name = requestBody.getName();
        String description = requestBody.getDescription();
        partService.addNewPart(name, description);
    }
}
