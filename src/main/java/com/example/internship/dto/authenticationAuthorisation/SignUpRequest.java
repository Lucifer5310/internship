package com.example.internship.dto.authenticationAuthorisation;

import lombok.Data;

@Data
public class SignUpRequest {

    private String username;
    private String email;
    private String password;
}
