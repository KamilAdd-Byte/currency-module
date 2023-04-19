package com.rates.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.rates.currency.*"})
public class CurrencyApp {
    public static void main(String[] args) {
       SpringApplication.run(CurrencyApp.class, args);
    }
}
