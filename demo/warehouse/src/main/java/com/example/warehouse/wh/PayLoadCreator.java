package com.example.warehouse.wh;

import jakarta.xml.soap.SOAPException;
import jakarta.xml.soap.SOAPMessage;

public interface PayLoadCreator {
    SOAPMessage create() throws SOAPException;
}
