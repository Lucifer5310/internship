package com.example.internship.service;

import com.example.internship.dao.Author;
import com.example.internship.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public void delete(long id) {
        authorRepository.deleteById(id);
    }

    public Author save(Author author){
        return authorRepository.save(author);
    }

    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(long id) {
        Optional<Author> byId = authorRepository.findById(id);
        return byId.orElseThrow();
    }
}
