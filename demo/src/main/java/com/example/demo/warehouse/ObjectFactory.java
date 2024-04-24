
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
     * Create an instance of {@link PickItemRequest }
     * 
     */
    public PickItemRequest createPickItemRequest() {
        return new PickItemRequest();
    }

    /**
     * Create an instance of {@link InsertItemRequest }
     * 
     */
    public InsertItemRequest createInsertItemRequest() {
        return new InsertItemRequest();
    }

    /**
     * Create an instance of {@link GetInventoryResponse.Inventory }
     * 
     */
    public GetInventoryResponse.Inventory createGetInventoryResponseInventory() {
        return new GetInventoryResponse.Inventory();
    }

}
