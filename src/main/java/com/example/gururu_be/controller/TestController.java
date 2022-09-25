package com.example.gururu_be.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${LOGNAME}")
    private String name;


    @GetMapping("/in")
    public String CheckIn() {
        return "check-in" + name ;
    }
}
