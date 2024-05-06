package com.example.warehouse.wh;

import jakarta.xml.soap.SOAPBody;
import jakarta.xml.soap.SOAPConnection;
import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;
import org.w3c.dom.NodeList;

import java.io.ByteArrayOutputStream;


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

}

