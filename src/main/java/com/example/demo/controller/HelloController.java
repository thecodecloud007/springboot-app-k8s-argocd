// HelloController.java
package com.example.demo.controller;

import com.example.demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    @Autowired
    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String home() {
        return "Spring Boot App is running!";
    }

    @GetMapping("/hello")
    public String hello() {
        return helloService.getGreeting();
    }

    @GetMapping("/hello/{name}")
    public String helloWithName(@PathVariable String name) {
        return helloService.getGreetingWithName(name);
    }

    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}