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
    requires java.persistence;
    requires jakarta.persistence;
    requires org.json;
    requires spring.data.commons;

    exports com.example.warehouse.warehouse;
    opens com.example.warehouse.warehouse to spring.beans;
    exports com.example.warehouse.config;
    opens com.example.warehouse.config to spring.beans;
    exports com.example.warehouse.endpoint;
    opens com.example.warehouse.endpoint to spring.beans;
    opens com.example.warehouse.controller to spring.beans;
    exports com.example.warehouse.wh;
}
