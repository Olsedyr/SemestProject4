package com.example.agv.agvConnection;

/**
 * Interface for controlling AGV (Automated Guided Vehicle) by
 * setting a program and then starting the program.
 */
public interface IAgvConnectionService {

    /**
     * Sets the program to be executed by the AGV.
     * <p>
     * Precondition:
     * - The program must be an enum of AgvPrograms.
     * <p>
     * Postcondition:
     * - The AGV's current program is set to the specified program.
     *
     * @param program the program to set for the AGV.
     */
    void setProgram(AgvPrograms program);

    /**
     * Starts the currently set program on the AGV.
     * <p>
     * Preconditions:
     * - A program must have been set
     * - The AGV must be ready to start the program
     * <p>
     * Postconditions:
     * - The AGV  executes the set program.
     * - If the program cannot start, an exception is thrown.
     */
    void startProgram();

    /**
     * Retrieves the current status of the AGV.
     * <p>
     * Preconditions:
     * - The AGV connection must be initialized and reachable.
     * <p>
     * Postconditions:
     * - Returns the current status of the AGV which includes:
     * int battery
     * String programName
     * int state
     * String timestamp
     * <p>
     * - If the AGV status cannot be retrieved, an appropriate exception is thrown.
     *
     * @return the current status of the AGV.
     */
    AgvStatus getAgvStatus();
}
