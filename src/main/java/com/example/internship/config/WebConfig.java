package com.example.internship.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:3000"); // Разрешённый источник (React фронтенд)
        config.addAllowedMethod("*"); // Разрешённые методы (GET, POST, DELETE и т.д.)
        config.addAllowedHeader("*"); // Разрешённые заголовки
        source.registerCorsConfiguration("/**", config); // Применить ко всем путям
        return new CorsFilter(source);
    }
}
