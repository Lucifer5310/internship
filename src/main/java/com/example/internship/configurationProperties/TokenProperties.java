package com.example.internship.configurationProperties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("token")
public class TokenProperties {

    private String jwt;
}