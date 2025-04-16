// HelloService.java
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    @Value("${app.greeting:Hello from Kubernetes!}")
    private String greeting;

    public String getGreeting() {
        return greeting;
    }

    public String getGreetingWithName(String name) {
        return greeting + " " + name;
    }
}