package com.example.product.service;

import com.example.agv.agvConnection.AgvConnection;
import com.example.agv.agvConnection.AgvPrograms;
import com.example.assembly.AssemblyRequests;
import com.example.product.model.Batch;
import com.example.product.model.Part;
import com.example.product.model.Product;
import com.example.product.model.RecipePart;
import com.example.product.repository.ProductRepository;
import com.example.warehouse.wh.WarehouseClient;
import jakarta.xml.soap.SOAPException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class BatchService {
    private final ProductRepository productRepository;
    AgvConnection agvConnection = AgvConnection.getInstance();
    AssemblyRequests assemblyRequests = new AssemblyRequests();
    WarehouseClient warehouseClient = new WarehouseClient();

    @Autowired
    public BatchService(ProductRepository productRepository) throws SOAPException {
        this.productRepository = productRepository;
    }



    public void CreateBatch(List<String> productNames) throws Exception {
        List<Product> products = new ArrayList<>();
        Batch batch = new Batch();
        for (String productName : productNames) {
            Product product = productRepository.findByName(productName);
            if (product != null) {
                products.add(product);
            } else {
                throw new Exception("Product named " + productName + " not found.");
            }
        }
        batch.setProducts(products);

        List<Part> parts = new ArrayList<>();
        for (Product product : batch.getProducts()){

            List <RecipePart> recipeParts = product.getRecipe().getRecipeParts();
            for (RecipePart recipePart : recipeParts){
                parts.add(recipePart.getPart());
                System.out.println(parts);
            }
        }
        // Convert part names from parts list to a set for efficient searching
        Set<String> partNames = new HashSet<>();
        for (Part part : parts) {
            partNames.add(part.getName());
        }

        System.out.println(agvConnection.getAgvStatus());
        agvConnection.setProgram(AgvPrograms.MoveToStorageOperation);
        System.out.println(agvConnection.getAgvStatus());
        List<String> inventoryItems = new ArrayList<>(warehouseClient.getInventoryAsNameStrings());

        // Identify missing parts
        Set<String> missingParts = partNames.stream()
                .filter(partName -> !inventoryItems.contains(partName))
                .collect(Collectors.toSet());

        if (!missingParts.isEmpty()) {
            handleMissingParts(missingParts);
        } else {
            System.out.println("All parts available in inventory.");
        }

    }

    private void handleMissingParts(Set<String> missingParts) {
        String message = "Missing parts in inventory: " + String.join(", ", missingParts);
        System.out.println(message);

    }
}
