module Production {
    uses com.example.agv.agvConnection.IAgvConnectionService;
    requires assembly.module;
    requires agv.module;
    requires warehouse.module;
    requires product.module;
    requires spring.web;
    requires spring.beans;
    requires spring.context;
    requires spring.tx;
    requires spring.data.commons;


    exports com.example.production;
}