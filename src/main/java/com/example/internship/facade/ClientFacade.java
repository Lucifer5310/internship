package com.example.internship.facade;

import com.example.internship.dao.entity.Users;
import com.example.internship.dto.client.*;
import com.example.internship.dao.entity.Client;
import com.example.internship.dao.entity.Book;
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
public class ClientFacade {

    private final ClientService clientService;
    private final UserService userService;

    public Iterable<ClientGetResponse> findAll() {
        return StreamSupport.stream(clientService.findAll().spliterator(), false)
                .map(client -> new ClientGetResponse(
                        client.getId(),
                        client.getFirstName(),
                        client.getMiddleName()))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        clientService.delete(id);
    }

    public ClientEditResponse saveEditRequest(ClientEditRequest clientEditRequest, long id){
        Client client = clientService.findById(id);
        client.setFirstName(clientEditRequest.getFirstName());
        client.setMiddleName(clientEditRequest.getMiddleName());

        Users user = client.getUser();
        user.setEmail(clientEditRequest.getUserEmail());

        Users userSaved = userService.save(user);
        Client saved = clientService.save(client);
        log.info("Client is edited");

        return ClientEditResponse.builder()
                .firstName(saved.getFirstName())
                .middleName(saved.getMiddleName())
                .userEmail(userSaved.getEmail())
                .build();
    }

    public ClientGetByIdResponse findById (long id) {
        Client client = clientService.findById(id);
        Users user = client.getUser();

        return ClientGetByIdResponse.builder()
                .firstName(client.getFirstName())
                .middleName(client.getMiddleName())
                .userEmail(user.getEmail())
                .userName(user.getUsername())
                .bookNameList(client.getBooks().stream()
                        .map(Book::getName)
                        .collect(Collectors.toList()))
                .build();
    }
}
