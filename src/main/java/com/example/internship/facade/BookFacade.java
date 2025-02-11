package com.example.internship.facade;

import com.example.internship.dto.book.BookCreateRequest;
import com.example.internship.dto.book.BookCreateResponse;
import com.example.internship.dto.book.BookEditRequest;
import com.example.internship.dto.book.BookEditResponse;
import com.example.internship.dao.entity.Book;
import com.example.internship.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookFacade {

    private final BookService bookService;

    public Iterable<Book> findAll() {
        return bookService.findAll();
    }

    public void delete(long id) {
        bookService.delete(id);
    }

    public BookCreateResponse savePostRequest(BookCreateRequest bookCreateRequest){
        final Book book = new Book();
        book.setName(bookCreateRequest.getName());
        book.setGenre(bookCreateRequest.getGenre());
        book.setRead(bookCreateRequest.isRead());

        Book saved = bookService.save(book);
        log.info("Book is added");

        return BookCreateResponse.builder()
                .name(saved.getName())
                .genre(saved.getGenre())
                .isRead(saved.isRead())
                .build();
    }

    public BookEditResponse saveEditRequest(BookEditRequest bookEditRequest, long id){
        Book book = bookService.findById(id);
        book.setName(bookEditRequest.getName());
        book.setGenre(bookEditRequest.getGenre());
        book.setRead(bookEditRequest.isRead());

        Book saved = bookService.save(book);
        log.info("Book is edited");

        return BookEditResponse.builder()
                .name(saved.getName())
                .genre(saved.getGenre())
                .isRead(saved.isRead())
                .build();
    }
}
