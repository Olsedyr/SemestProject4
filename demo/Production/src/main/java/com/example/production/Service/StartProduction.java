package com.example.production.Service;

import com.example.product.model.Product;
import com.example.production.Service.States.AgvPickParts;
import com.example.production.Service.States.AgvToWarehouse;
import org.springframework.stereotype.Component;

@Component
public class StartProduction {

//    State 0: Start med at kør AGV til warehouse (done)
//    State 1: Ud fra recipe (Valgt i front-end) samle alle delene op (Tray-Id) (Niko)
//    State 2: AGV kør til assembly station (Kim)
//    State 3: Sætte delene i assembly station (Kim)
//    State 4: Assembly station skal samle (Oliver)
//    State 5: Gem i database “recipe, timestamp, succes” (Oliver)


    boolean isFinished=false;
    int state = 0;
    public void startProduction(Product product) {

        while (isFinished == false) {

            AgvToWarehouse agvToWarehouse = new AgvToWarehouse();
            AgvPickParts agvPickParts = new AgvPickParts();

            switch (state) {
                case 0:
                    if (agvToWarehouse.moveAgvToWarehouse() == true) {
                        state++;
                        System.out.println("state 0 finished");
                    }
                    break;
                case 1:
                    if (agvPickParts.agvPickPart(agvPickParts.getPartList(product)) == true) {
                        state++;
                        System.out.println("state 1 finished");
                    }
                    break;
                case 2:
                    state++;
                    System.out.println("state 2 finished");
                    break;
                case 3:
                    state++;
                    System.out.println("state 3 finished");
                    break;
                case 4:
                    state++;
                    System.out.println("state 4 finished");
                    break;
                case 5:
                    isFinished = true;
                    System.out.println("state 5 finished");
                    System.out.println("Production done");
                    break;
            }

        }
    }
}
