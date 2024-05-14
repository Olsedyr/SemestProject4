package com.example.production;

import java.time.LocalDateTime;

public class ProductionStatus {
    //private boolean completed;
    private boolean completedWithoutError;
    private String log;

    public ProductionStatus( boolean completedWithoutError) {

        this.completedWithoutError = completedWithoutError;
        this.log = "";
    }


    public boolean isCompletedWithoutError() {
        return completedWithoutError;
    }

    public void setCompletedWithoutError(boolean completedWithoutError) {
        this.completedWithoutError = completedWithoutError;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }

    public void appendToLog(String message) {
        LocalDateTime now = LocalDateTime.now();
        this.log += now.toString() + " - " + message + "\n"; // Append the new message with a newline for separation
    }
}
