package com.example.internship.dto.users;


import com.example.internship.dao.entity.enumerate.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEditResponse {

    private Role role;
}
