package com.example.internship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableConfigurationProperties
@EnableJpaRepositories(basePackages = { "com.example.internship.repository" })
public class InternshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(InternshipApplication.class, args);
    }

}
