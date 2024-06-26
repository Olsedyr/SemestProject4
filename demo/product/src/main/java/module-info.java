module product.module {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.data.jpa;
    requires com.fasterxml.jackson.databind;
    requires spring.context;
    requires spring.beans;
    requires spring.tx;
    requires jakarta.persistence;
    requires spring.data.commons;

    exports com.example.product;
    exports com.example.product.model;
    exports com.example.product.dto;
    exports com.example.product.repository;
    exports com.example.product.service;

}