package com.example.demo.config;


import com.example.demo.service.WarehouseService;
import com.example.demo.service.WarehouseServiceClient;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.List;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {

    @Bean
    public WarehouseService warehouseService() {
        return new WarehouseServiceClient();
    }

    @Bean
    public WebServiceTemplate webServiceTemplate(Jaxb2Marshaller marshaller) {
        WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
        webServiceTemplate.setMarshaller(marshaller);
        webServiceTemplate.setUnmarshaller(marshaller);
        return webServiceTemplate;
    }

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // Set context path to the package containing your generated classes from WSDL
        marshaller.setContextPath("warehouse");
        return marshaller;
    }

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/ws/*");
    }

    @Bean(name = "/warehouse")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema warehouseSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("WarehousePort");
        // The url for the WSDL file given in the technical specification
        wsdl11Definition.setLocationUri("http://localhost:8081/Service.asmx");
        wsdl11Definition.setTargetNamespace("http://example.org/warehouse");

        wsdl11Definition.setSchema(warehouseSchema);
        // Disable automatic WSDL generation, as we already have the desired WSDL file
        wsdl11Definition.setCreateSoap11Binding(false);
        wsdl11Definition.setCreateSoap12Binding(false);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema warehouseSchema() {
        return new SimpleXsdSchema(new ClassPathResource("warehouse.xsd"));
    }

    @Bean
    public PayloadLoggingInterceptor loggingInterceptor() {
        return new PayloadLoggingInterceptor();
    }

    @Override
    public void addInterceptors(List<EndpointInterceptor> interceptors) {
        interceptors.add(loggingInterceptor());
    }
}


