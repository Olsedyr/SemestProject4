package com.example.warehouse.warehouse;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import jakarta.xml.ws.Action;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 4.0.4
 * 2024-05-02T12:51:40.665+02:00
 * Generated source version: 4.0.4
 *
 */
@WebService(targetNamespace = "http://tempuri.org/", name = "IEmulatorService")
@XmlSeeAlso({ObjectFactory.class})
public interface IEmulatorService {

    @WebMethod(operationName = "InsertItem", action = "http://tempuri.org/IEmulatorService/InsertItem")
    @Action(input = "http://tempuri.org/IEmulatorService/InsertItem", output = "http://tempuri.org/IEmulatorService/InsertItemResponse")
    @RequestWrapper(localName = "InsertItem", targetNamespace = "http://tempuri.org/", className = "warehouse.InsertItem")
    @ResponseWrapper(localName = "InsertItemResponse", targetNamespace = "http://tempuri.org/", className = "warehouse.InsertItemResponse")
    @WebResult(name = "InsertItemResult", targetNamespace = "http://tempuri.org/")
    public java.lang.String insertItem(

        @WebParam(name = "trayId", targetNamespace = "http://tempuri.org/")
        int trayId,
        @WebParam(name = "name", targetNamespace = "http://tempuri.org/")
        java.lang.String name
    );

    @WebMethod(operationName = "PickItem", action = "http://tempuri.org/IEmulatorService/PickItem")
    @Action(input = "http://tempuri.org/IEmulatorService/PickItem", output = "http://tempuri.org/IEmulatorService/PickItemResponse")
    @RequestWrapper(localName = "PickItem", targetNamespace = "http://tempuri.org/", className = "warehouse.PickItem")
    @ResponseWrapper(localName = "PickItemResponse", targetNamespace = "http://tempuri.org/", className = "warehouse.PickItemResponse")
    @WebResult(name = "PickItemResult", targetNamespace = "http://tempuri.org/")
    public java.lang.String pickItem(

        @WebParam(name = "trayId", targetNamespace = "http://tempuri.org/")
        int trayId
    );

    @WebMethod(operationName = "GetInventory", action = "http://tempuri.org/IEmulatorService/GetInventory")
    @Action(input = "http://tempuri.org/IEmulatorService/GetInventory", output = "http://tempuri.org/IEmulatorService/GetInventoryResponse")
    @RequestWrapper(localName = "GetInventory", targetNamespace = "http://tempuri.org/", className = "warehouse.GetInventory")
    @ResponseWrapper(localName = "GetInventoryResponse", targetNamespace = "http://tempuri.org/", className = "warehouse.GetInventoryResponse")
    @WebResult(name = "GetInventoryResult", targetNamespace = "http://tempuri.org/")
    public java.lang.String getInventory()
;
}
