package com.example.internship.controller.dto.users;


import com.example.internship.dao.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserEditRequest {

    private String username;
    private String email;
    private long clientId;
}
