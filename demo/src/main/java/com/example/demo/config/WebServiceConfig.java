package com.example.demo.config;


import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "warehouse")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema warehouseSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("WarehousePort");
        wsdl11Definition.setLocationUri("http://localhost:8081/Service.asmx");
        wsdl11Definition.setTargetNamespace("http://example.org/warehouse");

        wsdl11Definition.setSchema(warehouseSchema);
        // Disable automatic WSDL generation
        wsdl11Definition.setCreateSoap11Binding(false);
        wsdl11Definition.setCreateSoap12Binding(false);
        return wsdl11Definition;
    }


    @Bean
    public XsdSchema warehouseSchema() {
        return new SimpleXsdSchema(new ClassPathResource("warehouse.xsd"));
    }
}


