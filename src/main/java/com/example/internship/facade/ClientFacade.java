package com.example.internship.facade;

import com.example.internship.controller.dto.client.ClientCreateRequest;
import com.example.internship.controller.dto.client.ClientCreateResponse;
import com.example.internship.controller.dto.client.ClientEditRequest;
import com.example.internship.controller.dto.client.ClientEditResponse;
import com.example.internship.dao.Client;
import com.example.internship.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientFacade {

    private final ClientService clientService;

    public Iterable<Client> findAll() {
        return clientService.findAll();
    }

    public void delete(Long id) {
        clientService.delete(id);
    }

    public ClientCreateResponse savePostRequest(ClientCreateRequest clientCreateRequest){
        final Client client = new Client();
        client.setFirstName(clientCreateRequest.getFirstName());
        client.setMiddleName(clientCreateRequest.getMiddleName());

        Client saved = clientService.save(client);

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

        return ClientEditResponse.builder()
                .firstName(saved.getFirstName())
                .middleName(saved.getMiddleName())
                .build();
    }
}
