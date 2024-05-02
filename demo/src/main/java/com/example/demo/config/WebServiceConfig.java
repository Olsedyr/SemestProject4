package com.example.demo.config;


import com.example.demo.service.WarehouseService;
import com.example.demo.service.WarehouseServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.server.endpoint.interceptor.PayloadLoggingInterceptor;
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


