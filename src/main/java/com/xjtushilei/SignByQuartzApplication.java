package com.xjtushilei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SignByQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(SignByQuartzApplication.class, args);
    }
}
