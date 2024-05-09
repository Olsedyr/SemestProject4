package com.example.warehouse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

public class WebServiceConfigTest {

    @Mock
    private ApplicationContext applicationContextMock;

    @InjectMocks
    private WebServiceConfig webServiceConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testWebServiceTemplate() {
        assertNotNull(webServiceConfig.webServiceTemplate(webServiceConfig.marshaller()));
    }

    @Test
    public void testMarshaller() {
        assertNotNull(webServiceConfig.marshaller());
    }

    @Test
    public void testMessageDispatcherServlet() {
        ServletRegistrationBean<MessageDispatcherServlet> registrationBean = webServiceConfig.messageDispatcherServlet(applicationContextMock);
        assertNotNull(registrationBean);
        assertNotNull(registrationBean.getServlet());
        assertNotNull(registrationBean.getUrlMappings());
        assertNotNull(registrationBean.getUrlMappings().contains("/ws/*"));
    }

    @Test
    public void testDefaultWsdl11Definition() {
        assertNotNull(webServiceConfig.defaultWsdl11Definition(webServiceConfig.warehouseSchema()));
    }

    @Test
    public void testWarehouseSchema() {
        assertNotNull(webServiceConfig.warehouseSchema());
    }

    @Test
    public void testLoggingInterceptor() {
        assertNotNull(webServiceConfig.loggingInterceptor());
    }

    @Test
    public void testAddInterceptors() {
        webServiceConfig.addInterceptors(Collections.emptyList());
    }
}