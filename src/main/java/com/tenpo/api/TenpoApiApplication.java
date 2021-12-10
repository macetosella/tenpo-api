package com.tenpo.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAsync
public class TenpoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TenpoApiApplication.class, args);
    }

}
