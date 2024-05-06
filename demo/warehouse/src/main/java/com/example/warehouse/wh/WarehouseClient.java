package com.example.warehouse.wh;

import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

import java.util.List;

public class WarehouseClient {


    SOAPRequestSender sender = new SOAPRequestSender();
    //String url = "http://localhost:8081/Service.asmx";
    String url = "http://localhost:8080/ws/warehouse";
    SOAPConnection connection = WarehouseConnection.getSoapConnection();
    public WarehouseClient() throws SOAPException {
    }

    public SOAPConnection getConnection() {
        return connection;
    }

    public void getInventoryRequest() throws Exception {
        GetInventory getInventoryMessage = new GetInventory();
        SOAPMessage getInventory = sender.sendSOAPRequest(connection, getInventoryMessage, url);
        String result = sender.extractResponse(getInventory);
        System.out.println("GetInventory Response: " + result);
    }

    public List<String> getInventoryAsNameStrings() throws Exception {
        GetInventory getInventoryMessage = new GetInventory();
        SOAPMessage soapMessage = sender.sendSOAPRequest(connection, getInventoryMessage, url);
        List<String> itemNames = sender.extractInventoryResponse(soapMessage);
        System.out.println("GetInventory Response: " + itemNames);
        return itemNames;
    }


    public void pickItemRequest(int trayId) throws Exception {
        PickItem pickItemMessage = new PickItem(trayId);
        SOAPMessage pickItem = sender.sendSOAPRequest(connection, pickItemMessage, url);
        String result = sender.extractResponse(pickItem);
        System.out.println("PickItem Response: " + result);
    }

    public void insertItemRequest(int trayId, String name) throws Exception {
        InsertItem insertItemMessage = new InsertItem(trayId,name);
        SOAPMessage insertItem = sender.sendSOAPRequest(connection, insertItemMessage, url);
        String result = sender.extractResponse(insertItem);
        System.out.println("InsertItem Response: " + result);
    }

    private void emptyAllTrays() {
        try {
            for (int i=1;i<=10;i++){
                pickItemRequest(i);
            }
        }catch (Exception ignored){
        }
    }


    public static void main(String[] args) {
        try {

            WarehouseClient warehouseClient = new WarehouseClient();
            warehouseClient.emptyAllTrays();
            warehouseClient.getInventoryRequest();
            //soapClient.pickItemRequest(2);
            warehouseClient.insertItemRequest(1,"Bolt");
            warehouseClient.insertItemRequest(2,"Wheel");
            warehouseClient.insertItemRequest(3,"Board");
            warehouseClient.insertItemRequest(4,"Item4");
            warehouseClient.insertItemRequest(5,"Skateboard 1");
            warehouseClient.insertItemRequest(6,"Item 6");
            warehouseClient.insertItemRequest(7,"Item 7");
            warehouseClient.insertItemRequest(8,"Item 8");
            warehouseClient.insertItemRequest(9,"Item 9");
            warehouseClient.insertItemRequest(10,"Item 10");

            warehouseClient.getInventoryRequest();
            warehouseClient.getConnection().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
