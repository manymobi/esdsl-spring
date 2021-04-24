package com.manymobi.esdsl.spring.example;

import com.manymobi.esdsl.spring.boot.autoconfigure.EnableEsdsl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEsdsl
@SpringBootApplication
public class EsdslSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsdslSpringApplication.class, args);
    }

}
