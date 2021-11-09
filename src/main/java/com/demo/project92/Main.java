package com.demo.project92;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

@RestController
class HomeController {
    @GetMapping(value = "/api/user")
    public String getUser() {
        return UUID.randomUUID().toString().substring(0, 7);
    }
}
