package com.example.internship.controller;

import com.example.internship.dto.users.UserEditRequest;
import com.example.internship.dto.users.UserEditResponse;
import com.example.internship.dao.entity.Users;
import com.example.internship.dto.users.UserGetResponse;
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

    @PutMapping(value = "/{id}")
    public UserEditResponse replaceUser(@RequestBody UserEditRequest userEditRequest,
                                        @PathVariable long id) {
        return userFacade.saveEditRequest(userEditRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userFacade.delete(id);
    }
}
