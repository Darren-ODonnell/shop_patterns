package com.jwt;

import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Log
@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        new SpringApplication(JwtApplication.class).run(JwtApplication.class, args);
    }

}
