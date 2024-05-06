package com.example.product.controller;

import com.example.product.service.BatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/batch")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from frontend
public class BatchController {

    BatchService batchService;

    public BatchController(BatchService batchService) {
        this.batchService = batchService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBatch(@RequestBody List<String> productNames) {
        try {
            batchService.CreateBatch(productNames);
            return ResponseEntity.ok("Batch created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error creating batch: " + e.getMessage());
        }
    }
}
