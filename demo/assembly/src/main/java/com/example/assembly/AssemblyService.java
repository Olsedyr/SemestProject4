package com.example.assembly;

import org.springframework.stereotype.Service;

@Service
public class AssemblyService implements IAssemblyService {
    private final AssemblyRequests assemblyRequests;

    public AssemblyService() {
        this.assemblyRequests = new AssemblyRequests();
    }

    @Override
    public void executeProgram(long processId) {
        assemblyRequests.executeProgram(processId);
    }

    @Override
    public void subscribeToStatus(AssemblyRequests.StatusCallback callback) {
        assemblyRequests.getStatus(callback);
    }

    @Override
    public void subscribeToHealthCheck(AssemblyRequests.StatusCallback callback) {
        assemblyRequests.getHealthCheck(callback);
    }

    @Override
    public void unsubscribeFromStatus() {
        assemblyRequests.unsubscribeStatus();
    }

    @Override
    public void unsubscribeFromHealthCheck() {
        assemblyRequests.unsubscribeHealthCheck();
    }
}