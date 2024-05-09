module productTest.module {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.data.jpa;
    requires com.fasterxml.jackson.databind;
    requires spring.context;
    requires spring.beans;
    requires spring.tx;
    requires jakarta.persistence;
    requires org.junit.jupiter.api;
    requires spring.boot.test;
    requires spring.test;
    requires org.mockito;
    requires org.mockito.junit.jupiter;


    exports com.example.product;
    exports com.example.product.model;

}