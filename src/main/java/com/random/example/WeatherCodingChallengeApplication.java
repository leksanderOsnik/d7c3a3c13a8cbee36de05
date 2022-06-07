package com.random.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan(basePackages = "com.random.example.*")

public class WeatherCodingChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherCodingChallengeApplication.class, args);
    }

}
