package com.example.internship.service;

import com.example.internship.dao.Book;
import com.example.internship.dao.Client;
import com.example.internship.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public void delete(Long id) {
        Client client = clientRepository.findById(id).get();
        List<Book> book = client.getBooks();
        for (int i = 0; i < book.size(); i++) {
            Book a = book.get(i);
            client.removeBook(a);
        }
        client.removeUser();
        clientRepository.delete(client);
    }

    public Client save(Client client){
        return clientRepository.save(client);
    }

    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(long id) {
        Optional<Client> byId = clientRepository.findById(id);
        return byId.orElseThrow();
    }
}
