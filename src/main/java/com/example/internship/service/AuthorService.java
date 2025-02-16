package com.example.internship.service;

import com.example.internship.dao.entity.Author;
import com.example.internship.dao.entity.Book;
import com.example.internship.dao.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthorService {

    private final AuthorRepository authorRepository;

    // very strange deleting of entity (you use CascadeType.ALL which includes CascadeType.REMOVE, so why you do something like that author.getBooks().clear())
    // Also too many @Transactional
    @Transactional
    public void delete(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();

            for (Book book : author.getBooks()) {
                book.setAuthor(null);
            }
            author.getBooks().clear();

            authorRepository.save(author);
            authorRepository.deleteById(id);
            log.info("Author is deleted");
        } else {
            throw new RuntimeException("Author with ID " + id + " not found"); // specify exception
        }
    }

    @Transactional
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Transactional(readOnly = true)
    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Author findById(long id) {
        Optional<Author> byId = authorRepository.findById(id);
        return byId.orElseThrow(() -> new UsernameNotFoundException("Author not found"));
    }
}
