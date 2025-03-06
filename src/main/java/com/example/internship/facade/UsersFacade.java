package com.example.internship.facade;

import com.example.internship.dto.users.UserEditRequest;
import com.example.internship.dto.users.UserEditResponse;
import com.example.internship.dao.entity.Users;
import com.example.internship.dto.users.UserGetResponse;
import com.example.internship.service.ClientService;
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
    private final ClientService clientService;

    public Iterable<UserGetResponse> findAll() {
        return StreamSupport.stream(userService.findAll().spliterator(), false)
                .map(users -> new UserGetResponse(
                        users.getUsername(),
                        users.getEmail(),
                        users.getRole(),
                        users.getClient() != null ? users.getClient().getFirstName() : "",
                        users.getClient() != null ? users.getClient().getMiddleName() : ""))
                .collect(Collectors.toList());
    }

    public

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
