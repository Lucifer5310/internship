package com.example.internship.service;

import com.example.internship.dao.Book;
import com.example.internship.dao.Client;
import com.example.internship.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    @Transactional
    public void delete(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();

            for (Book book : client.getBooks()) {
                book.setClient(null);
            }
            client.getBooks().clear();

            if (client.getUser() != null) {
                client.getUser().setClient(null);
            }

            clientRepository.save(client);
            clientRepository.deleteById(id);
        } else {
            throw new RuntimeException("Клиент с ID " + id + " не найден");
        }
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
