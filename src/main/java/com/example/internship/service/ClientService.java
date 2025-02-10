package com.example.internship.service;

import com.example.internship.dao.Book;
import com.example.internship.dao.Client;
import com.example.internship.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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
            log.info("Client is deleted");
        } else {
            throw new RuntimeException("Client with ID " + id + " not found");
        }
    }

    @Transactional
    public Client save(Client client){
        return clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public Iterable<Client> findAll() {
        return clientRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Client findById(long id) {
        Optional<Client> byId = clientRepository.findById(id);
        return byId.orElseThrow(() -> new UsernameNotFoundException("Client not found"));
    }
}
