package com.example.demo.controller;

import com.example.demo.AgvConnection.AgvPrograms;
import com.example.demo.AgvConnection.AgvStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.AgvConnection.AgvConnection;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/agv")
public class AgvController {

    private final AgvConnection agvConnection = AgvConnection.getInstance();

    @PutMapping("/program/move-to-charger")
    public void moveToChargerProgram() {
        agvConnection.setProgram(AgvPrograms.MoveToChargerOperation);
        agvConnection.startProgram();
    }

    @PutMapping("/program/move-to-assembly")
    public void moveToAssemblyProgram() {
        agvConnection.setProgram(AgvPrograms.MoveToAssemblyOperation);
        agvConnection.startProgram();
    }

    @PutMapping("/program/move-to-storage")
    public void moveToStorageProgram() {
        agvConnection.setProgram(AgvPrograms.MoveToStorageOperation);
        agvConnection.startProgram();
    }

    @PutMapping("/program/put-assembly")
    public void putAssemblyProgram() {
        agvConnection.setProgram(AgvPrograms.PutAssemblyOperation);
        agvConnection.startProgram();
    }

    @PutMapping("/program/pick-assembly")
    public void pickAssemblyProgram() {
        agvConnection.setProgram(AgvPrograms.PickAssemblyOperation);
        agvConnection.startProgram();
    }

    @PutMapping("/program/pick-warehouse")
    public void pickWarehouseProgram() {
        agvConnection.setProgram(AgvPrograms.PickWarehouseOperation);
        agvConnection.startProgram();
    }

    @PutMapping("/program/put-warehouse")
    public void putWarehouseProgram() {
        agvConnection.setProgram(AgvPrograms.PutWarehouseOperation);
        agvConnection.startProgram();
    }


    @GetMapping("/status")
    public AgvStatus getAgvStatus() {
        return agvConnection.getAgvStatus();
    }
}
