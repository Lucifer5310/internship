package com.example.internship.service;

import com.example.internship.dao.entity.Book;
import com.example.internship.dao.entity.Client;
import com.example.internship.dao.repository.ClientRepository;
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

        if (optionalClient.isPresent()) { // better to use orElseThrow() than checking isPresent()
            Client client = optionalClient.get();

            for (Book book : client.getBooks()) {
                book.setClient(null);
            }
            client.getBooks().clear();

            if (client.getUser() != null) {
                client.getUser().setClient(null);
            }

            clientRepository.save(client);    // why firstly you save and then delete
            clientRepository.deleteById(id);
            log.info("Client is deleted");
        } else {
            throw new RuntimeException("Client with ID " + id + " not found"); // specify exception
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
