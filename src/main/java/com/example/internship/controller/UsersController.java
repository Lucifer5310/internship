package com.example.internship.controller;

import com.example.internship.controller.dto.users.UserEditRequest;
import com.example.internship.controller.dto.users.UserEditResponse;
import com.example.internship.dao.Users;
import com.example.internship.facade.UsersFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersFacade userFacade;

    @GetMapping
    public Iterable<Users> findAllUsers() {
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
