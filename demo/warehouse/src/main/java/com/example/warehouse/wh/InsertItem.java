package com.example.warehouse.wh;

import jakarta.xml.soap.*;

public class InsertItem implements PayLoadCreator{
    private int trayId;
    private String name;


    public InsertItem(int trayId, String name) {
        this.trayId = trayId;
        this.name = name;
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
        envelope.addNamespaceDeclaration("temp", serverURI);

        // SOAP Body
        SOAPBody soapBody = envelope.getBody();
        SOAPElement soapBodyElem = soapBody.addChildElement("InsertItem", "temp");
        SOAPElement trayIdElem = soapBodyElem.addChildElement("trayId", "temp");
        trayIdElem.addTextNode(Integer.toString(this.trayId));
        SOAPElement nameElem = soapBodyElem.addChildElement("name", "temp");
        nameElem.addTextNode(this.name);

        soapMessage.saveChanges();

        return soapMessage;
    }
}
