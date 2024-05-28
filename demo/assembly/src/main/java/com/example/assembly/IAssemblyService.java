package com.example.assembly;

/**
 * Interface for controlling the Assembly Station.
 */
public interface IAssemblyService {

    /**
     * Executes an assembly program based on a given process ID.
     *
     * Precondition:
     * - The assembly system must be ready to start a new program.
     *
     * Postcondition:
     * - The assembly of the product corresponding to the processId is initiated.
     *
     * @param processId ID of the assembly process to execute. To start an unhealthy assembly process
     * you can use “9999”
     */
    void executeProgram(long processId);

    /**
     * Subscribes to status updates from the assembly system.
     *
     * Preconditions:
     * - A valid callback must be provided.
     *
     * Postconditions:
     * - The provided callback is registered to receive status updates.
     * - Status updates will be delivered to the callback as they occur.
     *
     * @param callback the callback to receive status updates.
     */
    void subscribeToStatus(AssemblyRequests.StatusCallback callback);

    /**
     * Subscribes to health check updates from the Assembly Station.
     *
     * Preconditions:
     * - A valid callback must be provided.
     *
     * Postconditions:
     * - The provided callback is registered to receive health check updates.
     * - Health check updates will be delivered to the callback as they occur.
     *
     * @param callback the callback to receive health check updates.
     */
    void subscribeToHealthCheck(AssemblyRequests.StatusCallback callback);

    /**
     * Unsubscribes from receiving status updates.
     *
     * Preconditions:
     * - The client must be previously subscribed to status updates.
     *
     * Postconditions:
     * - The client is no longer subscribed to status updates.
     * - No further status updates will be received.
     */
    void unsubscribeFromStatus();

    /**
     * Unsubscribes from receiving health check updates.
     *
     * Preconditions:
     * - The client must be previously subscribed to health check updates.
     *
     * Postconditions:
     * - The client is no longer subscribed to health check updates.
     * - No further health check updates will be received.
     */
    void unsubscribeFromHealthCheck();
}
