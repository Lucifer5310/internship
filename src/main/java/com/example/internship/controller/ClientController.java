package com.example.internship.controller;

import com.example.internship.dto.client.*;
import com.example.internship.dao.entity.Client;
import com.example.internship.facade.ClientFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientFacade clientFacade;

    @GetMapping
    public Iterable<ClientGetResponse> findAllClient() {
        return clientFacade.findAll();
    }

    @GetMapping(value = "/{id}")
    public ClientGetResponse findClientById(@PathVariable long id) {
        return clientFacade.findById(id);
    }

    @PostMapping
    public ClientCreateResponse addOneClient(@RequestBody ClientCreateRequest clientCreateRequest){
        return clientFacade.savePostRequest(clientCreateRequest);
    }

    @PutMapping(value = "/{id}")
    public ClientEditResponse replaceClient(@RequestBody ClientEditRequest clientEditRequest,
                                            @PathVariable long id) {
        return clientFacade.saveEditRequest(clientEditRequest, id);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientFacade.delete(id);
    }
}
