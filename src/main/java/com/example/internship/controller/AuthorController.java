package com.example.internship.controller;

import com.example.internship.dto.author.*;
import com.example.internship.facade.AuthorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorFacade authorFacade;

    @GetMapping
    public Iterable<AuthorGetResponse> findAllAuthor() {
        return authorFacade.findAll();
    }

    @GetMapping(value = "{id}")
    public AuthorGetByIdResponse findAuthorById(@PathVariable long id) {
        return authorFacade.findAuthorBuId(id);
    }

    @PostMapping
    public AuthorCreateResponse addOneAuthor(@RequestBody AuthorCreateRequest authorCreateRequest){
        return authorFacade.savePostRequest(authorCreateRequest);
    }

    @PutMapping(value = "/{id}")
    public AuthorEditResponse replaceAuthor(@RequestBody AuthorEditRequest authorEditRequest,
                                          @PathVariable long id) {
        return authorFacade.saveEditRequest(authorEditRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteAuthor(@PathVariable long id) {
        authorFacade.delete(id);
    }
}
