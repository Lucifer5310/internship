package com.example.internship.controller;

import com.example.internship.controller.dto.client.ClientCreateRequest;
import com.example.internship.controller.dto.client.ClientCreateResponse;
import com.example.internship.controller.dto.client.ClientEditRequest;
import com.example.internship.controller.dto.client.ClientEditResponse;
import com.example.internship.dao.Client;
import com.example.internship.facade.ClientFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientFacade clientFacade;

    @GetMapping
    public Iterable<Client> findAllClient() {
        return clientFacade.findAll();
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

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientFacade.delete(id);
    }
}
