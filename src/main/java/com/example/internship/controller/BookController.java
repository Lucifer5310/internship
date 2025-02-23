package com.example.internship.controller;

import com.example.internship.dto.book.*;
import com.example.internship.facade.BookFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookFacade bookFacade;

    @GetMapping
    public Iterable<BookGetResponse> findAllBook() {
        return bookFacade.findAll();
    }

    @PostMapping
    public BookCreateResponse addOneBook(@RequestBody BookCreateRequest bookCreateRequest){
        return bookFacade.savePostRequest(bookCreateRequest);
    }

    @PutMapping(value = "/{id}")
    public BookEditResponse replaceBook(@RequestBody BookEditRequest bookEditRequest,
                                        @PathVariable long id) {
        return bookFacade.saveEditRequest(bookEditRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable long id) {
        bookFacade.delete(id);
    }
}
