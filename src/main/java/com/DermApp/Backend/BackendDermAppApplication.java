package com.DermApp.Backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BackendDermAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendDermAppApplication.class, args);
    }

}
