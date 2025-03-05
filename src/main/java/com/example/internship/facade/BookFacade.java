package com.example.internship.facade;

import com.example.internship.dao.entity.Author;
import com.example.internship.dao.entity.Client;
import com.example.internship.dao.entity.Shelf;
import com.example.internship.dto.book.*;
import com.example.internship.dao.entity.Book;
import com.example.internship.service.AuthorService;
import com.example.internship.service.BookService;
import com.example.internship.service.ClientService;
import com.example.internship.service.ShelfService;
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
    private final ShelfService shelfService;
    private final ClientService clientService;
    private final AuthorService authorService;

    public Iterable<BookGetResponse> findAll() {
        return StreamSupport.stream(bookService.findAll().spliterator(), false)
                .map(book -> new BookGetResponse(
                        book.getId(),
                        book.getName(),
                        book.getGenre(),
                        book.getAuthor().getName(),
                        book.getShelf().getName(),
                        book.getShelf().getBookcase().getNumber(),
                        book.getImageName(),
                        book.isRead()))
                .collect(Collectors.toList());
    }

    public void delete(long id) {
        bookService.delete(id);
    }

    public BookCreateResponse savePostRequest(BookCreateRequest bookCreateRequest){
        Shelf shelf = shelfService.findByName(bookCreateRequest.getShelfName());
        Author author = authorService.findByName(bookCreateRequest.getAuthorName());

        final Book book = new Book();
        book.setName(bookCreateRequest.getName());
        book.setGenre(bookCreateRequest.getGenre());
        book.setImageName(bookCreateRequest.getImageName());
        book.setRead(bookCreateRequest.isRead());
        book.setShelf(shelf);
        book.setAuthor(author);
        book.setClient(null);

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
        Shelf shelf = shelfService.findByName(bookEditRequest.getShelfName());
        Author author = authorService.findByName(bookEditRequest.getAuthorName());
        Client client = clientService.findById(bookEditRequest.getClientId());

        book.setName(bookEditRequest.getName());
        book.setGenre(bookEditRequest.getGenre());
        book.setRead(bookEditRequest.isRead());
        book.setImageName(bookEditRequest.getImageName());
        book.setShelf(shelf);
        book.setAuthor(author);
        book.setClient(client);

        Book saved = bookService.save(book);
        log.info("Book is edited");

        return BookEditResponse.builder()
                .name(saved.getName())
                .genre(saved.getGenre())
                .imageName(saved.getImageName())
                .isRead(saved.isRead())
                .authorName(saved.getAuthor().getName())
                .shelfName(saved.getShelf().getName())
                .build();
    }

    public BookGetByIdResponse findById(long id) {
        Book book = bookService.findById(id);
        return BookGetByIdResponse.builder()
                .name(book.getName())
                .genre(book.getGenre())
                .imageName(book.getImageName())
                .authorName(book.getAuthor().getName())
                .shelfName(book.getShelf().getName())
                .clientId(book.getClient().getId())
                .isRead(book.isRead())
                .build();
    }
}
