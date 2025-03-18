package com.example.internship.dto.jwt;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;
}
