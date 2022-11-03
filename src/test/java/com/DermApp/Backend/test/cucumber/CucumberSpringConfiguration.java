package com.DermApp.Backend.test.cucumber;

import com.DermApp.Backend.BackendDermAppApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(classes = BackendDermAppApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = BackendDermAppApplication.class,
        loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {
}
