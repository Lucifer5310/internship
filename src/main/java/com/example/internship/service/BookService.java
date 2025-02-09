package com.example.internship.service;

import com.example.internship.dao.Book;
import com.example.internship.dao.Client;
import com.example.internship.repository.BookRepository;
import com.example.internship.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;

    public void delete(long id) {
        /*Optional<Book> book = bookRepository.findById(id);
        Book book1 = book.get();
        Optional<Client> client = clientRepository.findById(book1.getClient().getId());
        Client client1 = client.get();
        client1.removeBook(book1);*/
        bookRepository.deleteById(id);
    }

    public Book save(Book book){
        return bookRepository.save(book);
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(long id) {
        Optional<Book> byId = bookRepository.findById(id);
        return byId.orElseThrow();
    }
}
