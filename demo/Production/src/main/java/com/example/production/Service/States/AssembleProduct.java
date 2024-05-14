package com.example.production.Service.States;

import com.example.assembly.AssemblyRequests;
import com.example.production.Service.ProductionStates;

// State 4
public class AssembleProduct extends ProductionStates {

    public void executeAssemblyProgram(int processId) {
        AssemblyRequests assemblyRequests = new AssemblyRequests();
        assemblyRequests.executeProgram(processId);
        System.out.println("Assembly program executed with ProcessID: " + processId);
    }
}
