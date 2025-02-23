package com.example.internship.facade;

import com.example.internship.dto.client.*;
import com.example.internship.dao.entity.Client;
import com.example.internship.dao.entity.Book;
import com.example.internship.service.ClientService;
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

    public Iterable<ClientGetResponse> findAll() {
        return StreamSupport.stream(clientService.findAll().spliterator(), false)
                .map(client -> new ClientGetResponse(
                        client.getFirstName(),
                        client.getMiddleName(),
                        client.getBooks().stream()
                                .map(Book::getName)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        clientService.delete(id);
    }

    public ClientCreateResponse savePostRequest(ClientCreateRequest clientCreateRequest){
        final Client client = new Client();
        client.setFirstName(clientCreateRequest.getFirstName());
        client.setMiddleName(clientCreateRequest.getMiddleName());

        Client saved = clientService.save(client);
        log.info("Client is added");

        return ClientCreateResponse.builder()
                .firstName(saved.getFirstName())
                .middleName(saved.getMiddleName())
                .build();
    }

    public ClientEditResponse saveEditRequest(ClientEditRequest clientEditRequest, long id){
        Client client = clientService.findById(id);
        client.setFirstName(clientEditRequest.getFirstName());
        client.setMiddleName(clientEditRequest.getMiddleName());

        Client saved = clientService.save(client);
        log.info("Client is edited");

        return ClientEditResponse.builder()
                .firstName(saved.getFirstName())
                .middleName(saved.getMiddleName())
                .build();
    }
}
