package com.example.demo.endpoint;

import com.example.demo.service.TestDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestDataEndpoint {

    @Autowired
    private TestDBService testDBService;

    @PostMapping("/createTestData")
    public ResponseEntity<String> createTestData() {
        testDBService.createTestData();
        return ResponseEntity.ok("Test data created successfully.");
    }
}
