package com.example.internship.service;

import com.example.internship.dao.Book;
import com.example.internship.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
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
        } else {
            throw new RuntimeException("Книга с ID " + id + " не найден");
        }
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
