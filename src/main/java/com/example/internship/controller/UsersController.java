package com.example.internship.controller;

import com.example.internship.dto.users.UserEditRequest;
import com.example.internship.dto.users.UserEditResponse;
import com.example.internship.dao.entity.Users;
import com.example.internship.facade.UsersFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersFacade userFacade;

    // Return entity is bad practice, create dto
    @GetMapping
    public Iterable<Users> findAllUsers() {
        return userFacade.findAll();
    }

    @PutMapping(value = "/{id}")
    public UserEditResponse replaceUser(@RequestBody UserEditRequest userEditRequest, // think about validation
                                        @PathVariable long id) {
        return userFacade.saveEditRequest(userEditRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable long id) {
        userFacade.delete(id);
    }
}
