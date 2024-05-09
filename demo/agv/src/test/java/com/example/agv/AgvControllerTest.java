package com.example.agv;

import com.example.agv.AgvController;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.agv.agvConnection.AgvStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/agv")
public class AgvControllerTest {

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
