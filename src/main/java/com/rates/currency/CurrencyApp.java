package com.rates.currency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.rates.currency.*"})
@EnableSwagger2
public class CurrencyApp {
    public static void main(String[] args) {
       SpringApplication.run(CurrencyApp.class, args);
    }
}
