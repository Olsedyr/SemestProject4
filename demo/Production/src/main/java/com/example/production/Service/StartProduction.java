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
    AgvPickAssembly agvPickAssembly;
    @Autowired
    AgvPutWarehouse agvPutWarehouse;
    @Autowired
    SaveToDB saveToDB;
    @Autowired
    AssembleProduct assembleProduct;



    public void startProduction(Product product) {

        Batch batch = saveToDB.addBatchToDB(product);
        boolean isFinished = false;
        boolean isFailed = false;
        int state = 0;

        while (!isFinished && !isFailed) {


            switch (state) {
                case 0:
                    agvToCharger.moveAgvToCharger(50, batch); // move to charger if battery is less than 50 %
                    ProductionStatus productionStatus0 = agvToWarehouse.moveAgvToWarehouse();
                    batch.appendToLogNoTimeStamp(productionStatus0.getLog());
                    if (productionStatus0.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 0 finished");
                    } else isFailed = true;
                    break;
                case 1:
                    agvToCharger.moveAgvToCharger(5, batch);
                    ProductionStatus productionStatus1 = agvPickParts.agvPickPart(agvPickParts.getPartList(product));
                    batch.appendToLogNoTimeStamp(productionStatus1.getLog());
                    batchRepository.save(batch);
                    if (productionStatus1.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 1 finished");
                    } else isFailed = true;
                    break;
                case 2:
                    agvToCharger.moveAgvToCharger(10, batch);
                    ProductionStatus productionStatus2 = agvToAssembly.moveAgvToAssembly();
                    batch.appendToLogNoTimeStamp(productionStatus2.getLog());
                    batchRepository.save(batch);
                    if (productionStatus2.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 2 finished");
                    } else isFailed = true;
                    break;
                case 3:
                    agvToCharger.moveAgvToCharger(10, batch);
                    ProductionStatus productionStatus3 = agvPutAssembly.agvPutPart(agvPutAssembly.getPartList(product));
                    batch.appendToLogNoTimeStamp(productionStatus3.getLog());
                    batchRepository.save(batch);
                    if (productionStatus3.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 3 finished");
                    } else isFailed = true;
                    break;
                case 4:
                    ProductionStatus productionStatus4 = assembleProduct.executeAssemblyProgram(batch);
                    batch.appendToLogNoTimeStamp(productionStatus4.getLog());
                    batchRepository.save(batch);
                    if (productionStatus4.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 4 finished");
                    } else isFailed = true;
                    break;
                case 5:
                    agvToCharger.moveAgvToCharger(5, batch);
                    ProductionStatus productionStatus5 = agvPickAssembly.agvPickAssembly(product);
                    batch.appendToLogNoTimeStamp(productionStatus5.getLog());
                    batchRepository.save(batch);
                    if (productionStatus5.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 5 finished");
                    } else isFailed = true;
                    break;
                case 6:
                    agvToCharger.moveAgvToCharger(5, batch); // move to charger if battery is less than 50 %
                    ProductionStatus productionStatus6 = agvToWarehouse.moveAgvToWarehouse();
                    batch.appendToLogNoTimeStamp(productionStatus6.getLog());
                    if (productionStatus6.isCompletedWithoutError() == true) {
                        state++;
                        System.out.println("state 6 finished");
                    } else isFailed = true;
                    break;
                case 7:
                    isFinished = true;
                    System.out.println("state 7 finished");
                    System.out.println("Production done");
                    batch.setCompleted(true);
                    agvToCharger.moveAgvToCharger(50, batch);

                    break;
            }
        }
    }
}
