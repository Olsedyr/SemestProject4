package com.example.demo.Controller;
import com.example.demo.Assembly.OperationRequest;
import com.example.demo.service.AssemblyStationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssemblyStationController {

    private final AssemblyStationService assemblyStationService;

    public AssemblyStationController(AssemblyStationService assemblyStationService) {
        this.assemblyStationService = assemblyStationService;
    }

    @PostMapping("/executeOperation")
    public void executeOperation(@RequestBody OperationRequest operationRequest) {
        // Call a method in AssemblyStationService to execute the operation
        assemblyStationService.executeOperation(operationRequest.getProcessId());
    }

    // Other REST endpoints for controlling the assembly station can be defined here
}