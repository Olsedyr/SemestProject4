package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MainSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.example.MainSpringBootApplication.class, args);
    }
}
