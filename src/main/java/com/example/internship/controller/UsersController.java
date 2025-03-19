package com.example.internship.controller;

import com.example.internship.dto.users.UserEditRequest;
import com.example.internship.dto.users.UserEditResponse;
import com.example.internship.dao.entity.Users;
import com.example.internship.dto.users.UserGetResponse;
import com.example.internship.dto.users.UserGetRoleById;
import com.example.internship.facade.UsersFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersFacade userFacade;

    @GetMapping
    public Iterable<UserGetResponse> findAllUsers() {
        return userFacade.findAll();
    }

    @GetMapping(value = "/{id}")
    public UserGetRoleById findRoleById(@PathVariable Long id){
        return userFacade.findRoleById(id);
    }

    @PutMapping(value = "/{id}")
    public UserEditResponse replaceUser(@RequestBody UserEditRequest userEditRequest,
                                        @PathVariable Long id) {
        return userFacade.saveEditRequest(userEditRequest, id);
    }
}
