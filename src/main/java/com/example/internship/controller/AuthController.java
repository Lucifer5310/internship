package com.example.internship.controller;

import com.example.internship.dto.jwt.JwtAuthenticationResponse;
import com.example.internship.dto.authenticationAuthorisation.SignInRequest;
import com.example.internship.dto.authenticationAuthorisation.SignUpRequest;
import com.example.internship.dto.jwt.RefreshTokenRequest;
import com.example.internship.dto.jwt.RefreshTokenResponse;
import com.example.internship.service.AuthenticationService;
import com.example.internship.service.JwtService;
import com.example.internship.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody @Valid SignUpRequest request) {
        authenticationService.signUp(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        if (!refreshTokenService.validateRefreshToken(request.getRefreshToken())) {
            return ResponseEntity.status(403).body("Invalid refresh token");
        }

        String username = jwtService.extractUsername(request.getRefreshToken());
        String newAccessToken = jwtService.generateToken(username, false);
        //String newRefreshToken = refreshTokenService.createRefreshToken(username).getToken();

        return ResponseEntity.ok(new RefreshTokenResponse(newAccessToken));
    }
}