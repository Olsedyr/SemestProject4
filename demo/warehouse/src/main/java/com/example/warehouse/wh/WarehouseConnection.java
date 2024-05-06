package com.example.warehouse.wh;

import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPConnectionFactory;
import jakarta.xml.soap.SOAPException;

public class WarehouseConnection {

    // The URL to which the SOAP connection will be made
    //private static final String url = "http://localhost:8081/Service.asmx";
    private static final String url = "http://localhost:8080/ws/warehouse";

    private WarehouseConnection() {
    }

    // Static method to get a new SOAP connection
    public static SOAPConnection getSoapConnection() throws SOAPException {
        // Create a new connection factory instance and return a new connection
        SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
        return soapConnectionFactory.createConnection();
    }

}

