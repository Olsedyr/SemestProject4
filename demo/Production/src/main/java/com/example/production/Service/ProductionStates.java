package com.example.production.Service;

public abstract class ProductionStates {

//    Start med at kør AGV til warehouse
//    Ud fra recipe (Valgt i front-end) samle alle delene op (Tray-Id)
//    AGV kør til assembly station
//    Sætte delene i assembly station
//    Assembly station skal samle
//    Gem i database “recipe, timestamp, succes”

    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
