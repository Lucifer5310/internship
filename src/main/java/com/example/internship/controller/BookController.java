package com.example.internship.controller;

import com.example.internship.dto.book.BookCreateRequest;
import com.example.internship.dto.book.BookCreateResponse;
import com.example.internship.dto.book.BookEditRequest;
import com.example.internship.dto.book.BookEditResponse;
import com.example.internship.dao.entity.Book;
import com.example.internship.facade.BookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookFacade bookFacade;

    // Return entity is bad practice, create dto
    @GetMapping
    public Iterable<Book> findAllBook() {
        return bookFacade.findAll();
    }

    @PostMapping
    public BookCreateResponse addOneBook(@RequestBody BookCreateRequest bookCreateRequest){ // validation?
        return bookFacade.savePostRequest(bookCreateRequest);
    }

    @PutMapping(value = "/{id}")
    public BookEditResponse replaceBook(@RequestBody BookEditRequest bookEditRequest,  // validation?
                                        @PathVariable long id) {
        return bookFacade.saveEditRequest(bookEditRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookFacade.delete(id);
    }
}
