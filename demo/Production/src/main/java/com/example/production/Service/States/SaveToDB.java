package com.example.production.Service.States;

import com.example.product.model.Batch;
import com.example.product.model.Product;
import com.example.product.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

// State 5
@Service
public class SaveToDB {

    @Autowired
    BatchRepository batchRepository;

    // Oliver
    public Batch addBatchToDB(Product product) {
        Batch batch = new Batch();
        List<Product> productList = new ArrayList<>();
        productList.add(product);
//        batch.setProducts(productList);
        batchRepository.save(batch);
        return batch;
    }
}