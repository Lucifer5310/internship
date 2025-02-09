package com.example.internship.service;

import com.example.internship.dao.Author;
import com.example.internship.dao.Book;
import com.example.internship.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

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
        } else {
            throw new RuntimeException("Автор с ID " + id + " не найден");
        }
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
