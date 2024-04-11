package com.example.demo.AgvConnection;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AgvStatus {
    @JsonProperty("battery")
    int battery;
    @JsonProperty("program name")
    String programName;
    @JsonProperty("state")
    int state;
    @JsonProperty("timestamp")
    String timestamp;

    public AgvStatus() {
    }


    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "AgvStatus{" +
                "battery=" + battery +
                ", programName='" + programName + '\'' +
                ", state=" + state +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
