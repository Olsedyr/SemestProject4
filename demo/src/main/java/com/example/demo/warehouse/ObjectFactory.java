
package com.example.demo.warehouse;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the warehouse package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: warehouse
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetInventoryResponse }
     * 
     */
    public GetInventoryResponse createGetInventoryResponse() {
        return new GetInventoryResponse();
    }

    /**
     * Create an instance of {@link GetInventoryResponse.Inventory }
     * 
     */
    public GetInventoryResponse.Inventory createGetInventoryResponseInventory() {
        return new GetInventoryResponse.Inventory();
    }

    /**
     * Create an instance of {@link PickItemResponse }
     * 
     */
    public PickItemResponse createPickItemResponse() {
        return new PickItemResponse();
    }

    /**
     * Create an instance of {@link InsertItemResponse }
     * 
     */
    public InsertItemResponse createInsertItemResponse() {
        return new InsertItemResponse();
    }

    /**
     * Create an instance of {@link GetInventoryResponse.Inventory.Board }
     * 
     */
    public GetInventoryResponse.Inventory.Board createGetInventoryResponseInventoryBoard() {
        return new GetInventoryResponse.Inventory.Board();
    }

    /**
     * Create an instance of {@link GetInventoryResponse.Inventory.Wheels }
     * 
     */
    public GetInventoryResponse.Inventory.Wheels createGetInventoryResponseInventoryWheels() {
        return new GetInventoryResponse.Inventory.Wheels();
    }

    /**
     * Create an instance of {@link GetInventoryResponse.Inventory.Trucks }
     * 
     */
    public GetInventoryResponse.Inventory.Trucks createGetInventoryResponseInventoryTrucks() {
        return new GetInventoryResponse.Inventory.Trucks();
    }

    /**
     * Create an instance of {@link GetInventoryResponse.Inventory.KidsBoard }
     * 
     */
    public GetInventoryResponse.Inventory.KidsBoard createGetInventoryResponseInventoryKidsBoard() {
        return new GetInventoryResponse.Inventory.KidsBoard();
    }

    /**
     * Create an instance of {@link GetInventoryResponse.Inventory.KidsWheels }
     * 
     */
    public GetInventoryResponse.Inventory.KidsWheels createGetInventoryResponseInventoryKidsWheels() {
        return new GetInventoryResponse.Inventory.KidsWheels();
    }

    /**
     * Create an instance of {@link GetInventoryResponse.Inventory.KidsTrucks }
     * 
     */
    public GetInventoryResponse.Inventory.KidsTrucks createGetInventoryResponseInventoryKidsTrucks() {
        return new GetInventoryResponse.Inventory.KidsTrucks();
    }

}
