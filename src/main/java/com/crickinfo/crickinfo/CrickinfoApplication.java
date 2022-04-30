package com.crickinfo.crickinfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CrickinfoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrickinfoApplication.class, args);
    }

}
