package com.example.internship.service;

import com.example.internship.dao.entity.Book;
import com.example.internship.dao.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void delete(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            if (book.getAuthor() != null) {
                book.getAuthor().getBooks().remove(book);
                book.setAuthor(null);
            }

            if (book.getClient() != null) {
                book.getClient().getBooks().remove(book);
                book.setClient(null);
            }

            if (book.getShelf() != null) {
                book.getShelf().getBooks().remove(book);
                book.setShelf(null);
            }
            bookRepository.save(book);
            bookRepository.deleteById(id);
            log.info("Book is deleted");
        } else {
            throw new RuntimeException("Book with ID " + id + " not found");
        }
    }

    @Transactional
    public Book save(Book book){
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Book findById(long id) {
        Optional<Book> byId = bookRepository.findById(id);
        return byId.orElseThrow(() -> new UsernameNotFoundException("Book not found"));
    }
}
