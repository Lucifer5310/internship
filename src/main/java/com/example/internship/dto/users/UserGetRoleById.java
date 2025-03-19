package com.example.internship.dto.users;

import com.example.internship.dao.entity.enumerate.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserGetRoleById {

    private Role role;
}
