module agv.module {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.data.jpa;
    requires com.fasterxml.jackson.databind;
    requires org.junit.jupiter.api;
    requires spring.boot.test;
    requires spring.test;

    exports com.example.agv;
}
