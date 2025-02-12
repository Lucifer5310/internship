package com.example.internship.facade;

import com.example.internship.dto.users.UserEditRequest;
import com.example.internship.dto.users.UserEditResponse;
import com.example.internship.dao.entity.Users;
import com.example.internship.service.ClientService;
import com.example.internship.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UsersFacade {

    private final UserService userService;
    private final ClientService clientService;

    public Iterable<Users> findAll() {
        return userService.findAll();
    }

    public void delete(long id) {
        userService.delete(id);
    }

    public UserEditResponse saveEditRequest(UserEditRequest userEditRequest, long id){
        Users users = userService.findById(id);
        users.setUsername(userEditRequest.getUsername());
        users.setEmail(userEditRequest.getEmail());
        users.setClient(clientService.findById(userEditRequest.getClientId()));

        Users saved = userService.save(users);
        log.info("User is edited");

        return UserEditResponse.builder()
                .username(saved.getUsername())
                .email(saved.getEmail())
                .clientId(saved.getClient().getId())
                .build();
    }
}
