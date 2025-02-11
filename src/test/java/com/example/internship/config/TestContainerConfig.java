package com.example.internship.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

import javax.sql.DataSource;

@TestConfiguration
public class TestContainerConfig {

    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER =
            new PostgreSQLContainer<>("postgres:16.4")
                    .withDatabaseName("postgres")
                    .withUsername("postgres")
                    .withPassword("seva2201");

    static {
        POSTGRES_CONTAINER.start();
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(POSTGRES_CONTAINER.getJdbcUrl());
        dataSource.setUsername(POSTGRES_CONTAINER.getUsername());
        dataSource.setPassword(POSTGRES_CONTAINER.getPassword());
        return dataSource;
    }
}
