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

    exports com.example.warehouse;
}
