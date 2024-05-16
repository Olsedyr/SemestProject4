package com.example.production.Service.States;

import com.example.assembly.AssemblyRequests;
import com.example.assembly.AssemblyStatus;
import com.example.production.Service.ProductionStates;
import org.springframework.stereotype.Service;

// State 4

@Service
public class AssembleProduct extends ProductionStates {


    public void executeAssemblyProgram(int processId) {
        AssemblyStatus assemblyStatus = new AssemblyStatus();
        AssemblyRequests assemblyRequests = new AssemblyRequests();
        assemblyRequests.executeProgram(processId);
        assemblyStatus.getCurrentOperation();
        System.out.println("Assembly program executed with ProcessID: " + processId);
    }

}
