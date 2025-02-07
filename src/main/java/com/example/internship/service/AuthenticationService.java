package com.example.internship.service;

import com.example.internship.controller.dto.authenticationAuthorisation.JwtAuthenticationResponse;
import com.example.internship.controller.dto.authenticationAuthorisation.SignInRequest;
import com.example.internship.controller.dto.authenticationAuthorisation.SignUpRequest;
import com.example.internship.dao.Role;
import com.example.internship.dao.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponse signUp(SignUpRequest signUpRequest) {
        var user = Users.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .role(Role.ROLE_USER)
                .build();

        userService.save(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwt);
    }

    public JwtAuthenticationResponse signIn(SignInRequest signUpRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                signUpRequest.getUsername(), signUpRequest.getPassword()));

        var user = userService.userDetailsService().loadUserByUsername(signUpRequest.getUsername());
        var jwt = jwtService.generateToken(user);

        return new JwtAuthenticationResponse(jwt);
    }
}

