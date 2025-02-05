package com.example.internship.controller.dto.authenticationAuthorisation;

import lombok.Data;

@Data
public class SignInRequest {

    private String username;
    private String password;
}
