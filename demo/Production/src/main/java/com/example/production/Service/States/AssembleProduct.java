package com.example.production.Service.States;

import com.example.assembly.AssemblyRequests;
import com.example.assembly.AssemblyStatus;
import com.example.product.model.Batch;
import com.example.production.ProductionStatus;
import com.example.production.Service.ProductionStates;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

// State 4

@Service
public class AssembleProduct extends ProductionStates {


    public ProductionStatus executeAssemblyProgram(Batch batch) {
        ProductionStatus productionStatus = new ProductionStatus(false);
        AssemblyRequests assemblyRequests = new AssemblyRequests();
        CountDownLatch latch = new CountDownLatch(2); // Two conditions to be met for success
        AtomicBoolean isAssemblyStatusMet = new AtomicBoolean(false); // Atomic to handle concurrency safely
        AtomicBoolean isHealthStatusMet = new AtomicBoolean(false);

        AssemblyRequests.StatusCallback callback = new AssemblyRequests.StatusCallback() {
            @Override
            public void onStatusReceived(AssemblyStatus status) {
                System.out.println("Received Assembly Status: " + status);
                if (status.getState() == 0 && status.getCurrentOperation() == batch.getId()) {
                    isAssemblyStatusMet.set(true);
                    if (isHealthStatusMet.get()) {
                        latch.countDown(); // Both conditions must be satisfied
                    }
                }
            }

            @Override
            public void onHealthReceived(String health) {
                System.out.println("Received Assembly Health Check: " + health);
                if ("\"{'IsHealthy': true}\"".equals(health)) {
                    isHealthStatusMet.set(true);
                    if (isAssemblyStatusMet.get()) {
                        latch.countDown(); // Both conditions must be satisfied
                    }
                }
            }
        };

        assemblyRequests.executeProgram(batch.getId());
        System.out.println("Assembly program started with ProcessID: " + batch.getId());
        productionStatus.appendToLog("Assembly program started with ProcessID: " + batch.getId());

        assemblyRequests.getHealthCheck(callback);
        assemblyRequests.getStatus(callback);

        try {
            // Wait for the latch to be decremented twice, or timeout after 20 seconds
            boolean success = latch.await(20, TimeUnit.SECONDS);
            if (success && isAssemblyStatusMet.get() && isHealthStatusMet.get()) {
                productionStatus.setCompletedWithoutError(true);
                System.out.println("Assembly program successfully executed with ProcessID: " + batch.getId());
                productionStatus.appendToLog("Assembly program successfully executed with ProcessID: " + batch.getId());
            } else {
                System.out.println("Assembly program did not finish in time.");
                productionStatus.appendToLog("Assembly program did not finish in time.");
                productionStatus.setCompletedWithoutError(false);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Interrupted while waiting for assembly program.");
            productionStatus.appendToLog("Interrupted while waiting for assembly program.");
            productionStatus.setCompletedWithoutError(false);
        }

        // Unsubscribe from topics
        assemblyRequests.unsubscribeHealthCheck();
        assemblyRequests.unsubscribeStatus();

        return productionStatus;
    }

}
