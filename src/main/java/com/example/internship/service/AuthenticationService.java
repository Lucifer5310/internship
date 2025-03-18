package com.example.internship.service;

import com.example.internship.dao.entity.Client;
import com.example.internship.dto.jwt.JwtAuthenticationResponse;
import com.example.internship.dto.authenticationAuthorisation.SignInRequest;
import com.example.internship.dto.authenticationAuthorisation.SignUpRequest;
import com.example.internship.dao.entity.enumerate.Role;
import com.example.internship.dao.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final ClientService clientService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public void signUp(SignUpRequest signUpRequest) {
        var user = Users.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.ROLE_USER)
                .client(null)
                .build();
        userService.save(user);

        var client = Client.builder()
                .firstName(signUpRequest.getFirstName())
                .middleName(signUpRequest.getMiddleName())
                .build();
        clientService.save(client);

        user.setClient(client);
        userService.save(user);
    }

    @Transactional
    public JwtAuthenticationResponse signIn(SignInRequest signUpRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signUpRequest.getUsername(), signUpRequest.getPassword()));

        var jwt = jwtService.generateToken(signUpRequest.getUsername(), false);
        String refreshToken = refreshTokenService.createRefreshToken(signUpRequest.getUsername()).getToken();

        return new JwtAuthenticationResponse(jwt, refreshToken);
    }
}

