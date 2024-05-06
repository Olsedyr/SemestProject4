package com.example.warehouse.wh;


import jakarta.xml.soap.*;

public class GetInventory implements PayLoadCreator {

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
        SOAPElement soapBodyElem = soapBody.addChildElement("GetInventory", "tempuri");
        soapBodyElem.setAttribute("xmlns", serverURI);

        soapMessage.saveChanges();

        return soapMessage;
    }
}
