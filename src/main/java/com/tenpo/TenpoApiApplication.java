package com.tenpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class TenpoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenpoApiApplication.class, args);
    }

}
