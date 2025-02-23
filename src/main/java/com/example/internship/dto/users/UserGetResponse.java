package com.example.internship.dto.users;

import com.example.internship.dao.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserGetResponse {

    private String username;
    private String email;
    private Role role;
    private String clientFirstName;
    private String clientMiddleName;
}
