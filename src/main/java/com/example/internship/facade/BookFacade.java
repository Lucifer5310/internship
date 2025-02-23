package com.example.internship.facade;

import com.example.internship.dto.author.AuthorGetResponse;
import com.example.internship.dto.book.*;
import com.example.internship.dao.entity.Book;
import com.example.internship.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookFacade {

    private final BookService bookService;

    public Iterable<BookGetResponse> findAll() {
        return StreamSupport.stream(bookService.findAll().spliterator(), false)
                .map(book -> new BookGetResponse(
                        book.getName(),
                        book.getGenre(),
                        book.getAuthor().getName(),
                        book.getShelf().getName(),
                        book.getShelf().getBookcase().getNumber(),
                        book.isRead()))
                .collect(Collectors.toList());
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
