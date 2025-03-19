package com.example.internship.facade;

import com.example.internship.dto.users.UserEditRequest;
import com.example.internship.dto.users.UserEditResponse;
import com.example.internship.dao.entity.Users;
import com.example.internship.dto.users.UserGetResponse;
import com.example.internship.dto.users.UserGetRoleById;
import com.example.internship.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsersFacade {

    private final UserService userService;

    public Iterable<UserGetResponse> findAll() {
        return StreamSupport.stream(userService.findAll().spliterator(), false)
                .map(users -> new UserGetResponse(
                        users.getId(),
                        users.getUsername(),
                        users.getEmail(),
                        users.getRole(),
                        users.getClient() != null ? users.getClient().getFirstName() : "",
                        users.getClient() != null ? users.getClient().getMiddleName() : ""))
                .collect(Collectors.toList());
    }

    public UserEditResponse saveEditRequest(UserEditRequest userEditRequest, long id){
        Users users = userService.findById(id);
        users.setRole(userEditRequest.getRole());

        Users saved = userService.save(users);
        log.info("User is edited");

        return UserEditResponse.builder()
                .role(saved.getRole())
                .build();
    }

    public UserGetRoleById findRoleById(Long id) {
        return UserGetRoleById.builder()
                .role(userService.findById(id).getRole())
                .build();
    }
}
