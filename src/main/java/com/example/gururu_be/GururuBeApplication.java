package com.example.gururu_be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GururuBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GururuBeApplication.class, args);
    }

}
