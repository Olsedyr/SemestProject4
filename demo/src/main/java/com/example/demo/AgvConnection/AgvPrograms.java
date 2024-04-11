package com.example.demo.AgvConnection;

public enum AgvPrograms {
    MoveToChargerOperation("MoveToChargerOperation"), //Move the AGV to the charging station
    MoveToAssemblyOperation("MoveToAssemblyOperation"), //Move the AGV to the assembly station.
    MoveToStorageOperation("MoveToStorageOperation"), //Move the AGV to the warehouse.
    PutAssemblyOperation("PutAssemblyOperation"), // Activate the robot arm to pick payload from AGV and place it at the assembly station.
    PickAssemblyOperation("PickAssemblyOperation"), // Activate the robot arm to pick payload atthe assembly station and place it on the AGV
    PickWarehouseOperation("PickWarehouseOperation"), // Activate the robot arm to pick payload fromthe warehouse outlet.
    PutWarehouseOperation("PutWarehouseOperation"); // Activate the robot arm to place an item atthe warehouse inlet.
    final String value;
    AgvPrograms(String value)
    {
        this.value = value;
    }
}
