package com.example.warehouse.wh;

import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

public class SOAPClient {


    SOAPRequestSender sender = new SOAPRequestSender();
    //String url = "http://localhost:8081/Service.asmx";
    String url = "http://localhost:8080/ws/warehouse";
    SOAPConnection connection = WarehouseConnection.getSoapConnection();
    public SOAPClient() throws SOAPException {
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

            SOAPClient soapClient = new SOAPClient();
            soapClient.emptyAllTrays();
            soapClient.getInventoryRequest();
            //soapClient.pickItemRequest(2);
            soapClient.insertItemRequest(1,"Hej");
            soapClient.insertItemRequest(2,"med");
            soapClient.insertItemRequest(3,"dig");
            soapClient.insertItemRequest(4,"har");
            soapClient.insertItemRequest(5,"du");
            soapClient.insertItemRequest(6,"en");
            soapClient.insertItemRequest(7,"ko");
            soapClient.insertItemRequest(8,"i");
            soapClient.insertItemRequest(9,"haven");
            soapClient.insertItemRequest(10,"?");

            soapClient.getInventoryRequest();
            soapClient.getConnection().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
