package com.example.warehouse.wh;

import jakarta.xml.soap.*;

public class PickItem implements PayLoadCreator{
    private int trayId;


    public PickItem(int trayId) {
        this.trayId = trayId;
    }


    @Override
    public SOAPMessage create() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance();
        SOAPMessage soapMessage = messageFactory.createMessage();
        SOAPPart soapPart = soapMessage.getSOAPPart();

        // Assume we are creating a SOAP envelope for a specific operation
        String serverURI = "http://tempuri.org/";

        // SOAP Envelope
        SOAPEnvelope envelope = soapPart.getEnvelope();
        envelope.addNamespaceDeclaration("tempuri", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        soapBody.addNamespaceDeclaration("temp", "http://tempuri.org/");
        SOAPElement soapBodyElem = soapBody.addChildElement("PickItem", "temp");
        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("trayId", "temp");
        soapBodyElem1.addTextNode(Integer.toString(trayId));

        soapMessage.saveChanges();

        return soapMessage;
    }
}
