module assembly.module {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires spring.data.jpa;
    requires com.fasterxml.jackson.databind;
    requires org.eclipse.paho.client.mqttv3;
    requires org.junit.jupiter.api;
    requires org.mockito;
    requires assembly.module;


    exports com.example.assembly;
}
