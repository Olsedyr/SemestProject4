package com.example.warehouse.wh;

import com.example.warehouse.warehouse.GetInventoryResponse;
import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class SOAPRequestSender {

    public SOAPMessage sendSOAPRequest(SOAPConnection connection, PayLoadCreator payLoadCreator, String url) throws Exception {
        SOAPMessage soapResponse = connection.call(payLoadCreator.create(), url);
        return soapResponse;
    }


    //  Method to extract and return response body
    public String extractResponse(SOAPMessage response) throws SOAPException {
        SOAPBody body = response.getSOAPBody();
        return body.getTextContent();
    }


    public String responseToString(SOAPMessage response) {
        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            response.writeTo(out);
            return out.toString();}catch(Exception e){return "";}
    }


    public List<String> extractInventoryResponse(SOAPMessage response) throws SOAPException {
        try {
            SOAPBody body = response.getSOAPBody();
            NodeList results = body.getElementsByTagNameNS("http://tempuri.org/", "GetInventoryResult");
            if (results.getLength() > 0) {
                Node resultNode = results.item(0);
                if (resultNode != null) {
                    String resultContent = resultNode.getTextContent();
                    JSONObject json = new JSONObject(resultContent);
                    JSONArray inventoryItems = json.getJSONArray("Inventory");
                    List<String> itemNames = new ArrayList<>();
                    for (int i = 0; i < inventoryItems.length(); i++) {
                        JSONObject item = inventoryItems.getJSONObject(i);
                        itemNames.add(item.getString("Content"));
                    }
                    return itemNames;
                }
            }
            throw new SOAPException("No valid inventory in SOAP response.");
        } catch (Exception e) {
            throw new SOAPException("Error processing SOAP response", e);
        }
    }

}

