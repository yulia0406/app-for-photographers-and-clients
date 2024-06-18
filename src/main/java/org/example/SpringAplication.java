package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class SpringAplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringAplication.class);
    }
}
