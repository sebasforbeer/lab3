package com.lab3.seva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SevaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SevaApplication.class, args);
    }

}
