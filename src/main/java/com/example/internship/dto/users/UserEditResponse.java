package com.example.internship.dto.users;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEditResponse {

    private String username;
    private String email;
    private long clientId;
}
