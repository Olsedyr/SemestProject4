module agv.module {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.data.jpa;
    requires com.fasterxml.jackson.databind;
    requires spring.context;
    requires spring.beans;

    exports com.example.agv;
    exports com.example.agv.agvConnection;
}
