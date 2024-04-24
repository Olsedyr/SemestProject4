package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssemblyStationController {
/*
    private final AssemblyStationService assemblyStationService;
    private final ObjectMapper objectMapper;

    public AssemblyStationController(AssemblyStationService assemblyStationService, ObjectMapper objectMapper) {
        this.assemblyStationService = assemblyStationService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/publish")
    public ResponseEntity<?> publish(@RequestBody String mqttMessage) {
        try {
            OperationRequest operationRequest = objectMapper.readValue(mqttMessage, OperationRequest.class);
            assemblyStationService.executeOperation(operationRequest.getProcessId());
            return ResponseEntity.ok("Success");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/executeOperation")
    public ResponseEntity<?> executeOperation(@RequestBody OperationRequest operationRequest) {
        try {
            assemblyStationService.executeOperation(operationRequest.getProcessId());
            return ResponseEntity.ok("Operation executed successfully for process ID: " + operationRequest.getProcessId());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Other REST endpoints for controlling the assembly station can be defined here

 */
}