package com.example.production.Service;

import com.example.product.model.Batch;
import com.example.product.model.Product;
import com.example.product.repository.BatchRepository;
import com.example.production.ProductionStatus;
import com.example.production.Service.States.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Service
public class StartProduction {

//    State 0: Start med at kør AGV til warehouse (done)
//    State 1: Ud fra recipe (Valgt i front-end) samle alle delene op (Tray-Id) (Niko)
//    State 2: AGV kør til assembly station (Kim)
//    State 3: Sætte delene i assembly station (Kim)
//    State 4: Assembly station skal samle (Oliver)
//    State 5: Gem i database “recipe, timestamp, succes” (Oliver)


    boolean isFinished = false;

    @Autowired
    BatchRepository batchRepository;

    @Autowired
    AgvToWarehouse agvToWarehouse;
    @Autowired
    AgvPickParts agvPickParts;
    @Autowired
    AgvToAssembly agvToAssembly;
    @Autowired
    AgvToCharger agvToCharger;
    @Autowired
    AgvPutAssembly agvPutAssembly;
    @Autowired
    SaveToDB saveToDB;
    @Autowired
    AssembleProduct assembleProduct;
    int state = 0;


    public void startProduction(Product product) {

        Batch batch = saveToDB.addBatchToDB(product);


        while (isFinished == false) {


            switch (state) {
                case 0:
                    agvToCharger.moveAgvToCharger(50, batch); // move to charger if battery is less than 50 %
                    ProductionStatus productionStatus0 = agvToWarehouse.moveAgvToWarehouse();
                    batch.appendToLogNoTimeStamp(productionStatus0.getLog());
                    if (productionStatus0.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 0 finished");
                    }
                    break;
                case 1:
                    agvToCharger.moveAgvToCharger(5, batch);
                    ProductionStatus productionStatus1 = agvPickParts.agvPickPart(agvPickParts.getPartList(product));
                    batch.appendToLogNoTimeStamp(productionStatus1.getLog());
                    batchRepository.save(batch);
                    if (productionStatus1.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 1 finished");

                    }
                    break;
                case 2:
                    agvToCharger.moveAgvToCharger(10, batch);
                    ProductionStatus productionStatus2 = agvToAssembly.moveAgvToAssembly();
                    batch.appendToLogNoTimeStamp(productionStatus2.getLog());
                    batchRepository.save(batch);
                    if (productionStatus2.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 2 finished");
                    }
                    break;
                case 3:
                    agvToCharger.moveAgvToCharger(10, batch);
                    ProductionStatus productionStatus3 = agvPutAssembly.agvPutPart(agvPutAssembly.getPartList(product));
                    batch.appendToLogNoTimeStamp(productionStatus3.getLog());
                    batchRepository.save(batch);
                    if (productionStatus3.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 3 finished");
                    }
                    break;
                case 4:
                    agvToCharger.moveAgvToCharger(10, batch);
                    assembleProduct.executeAssemblyProgram(1);
                    state++;
                    System.out.println("state 4 finished");
                    break;
                case 5:
                    isFinished = true;
                    System.out.println("state 5 finished");
                    System.out.println("Production done");
                    batch.setCompleted(true);
                    agvToCharger.moveAgvToCharger(50, batch);
                    break;
            }
        }
        state = 0;
        isFinished = false;
    }
}
