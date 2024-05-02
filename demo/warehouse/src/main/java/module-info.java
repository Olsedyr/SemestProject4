module warehouse.module {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.data.jpa;
    requires com.fasterxml.jackson.databind;
    requires spring.context;
    requires spring.core;
    requires spring.ws.core;
    requires spring.xml;
    requires spring.beans;
    requires jakarta.xml.bind;
    requires spring.oxm;
    requires jakarta.xml.ws;
    requires java.logging;
    requires jakarta.servlet;

    exports com.example.warehouse;
    exports com.example.warehouse.warehouse;
    opens com.example.warehouse to spring.beans;
    opens com.example.warehouse.warehouse to spring.beans;
}
