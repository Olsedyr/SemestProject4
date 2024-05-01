package com.example.assembly;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AssemblyStatus {
    @JsonProperty("LastOperation")
    int lastOperation;
    @JsonProperty("CurrentOperation")
    int  currentOperation;
    @JsonProperty("State")
    int state;
    @JsonProperty("TimeStamp")
    String timeStamp;

    public AssemblyStatus() {
    }

    public int getLastOperation() {
        return lastOperation;
    }

    public void setLastOperation(int lastOperation) {
        this.lastOperation = lastOperation;
    }

    public int getCurrentOperation() {
        return currentOperation;
    }

    public void setCurrentOperation(int currentOperation) {
        this.currentOperation = currentOperation;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTimestamp() {
        return timeStamp;
    }

    public void setTimestamp(String timestamp) {
        this.timeStamp = timestamp;
    }

    @Override
    public String toString() {
        return "AssemblyStatus{" +
                "lastOperation=" + lastOperation +
                ", currentOperation=" + currentOperation +
                ", state=" + state +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
