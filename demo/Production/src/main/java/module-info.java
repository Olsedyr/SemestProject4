module Production {
    requires assembly.module;
    requires agv.module;
    requires warehouse.module;
    requires product.module;
    requires spring.web;
    requires spring.beans;
    requires spring.context;


    exports com.example.production;
}