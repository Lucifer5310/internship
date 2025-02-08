package com.example.internship.controller;

import com.example.internship.controller.dto.author.AuthorCreateRequest;
import com.example.internship.controller.dto.author.AuthorCreateResponse;
import com.example.internship.controller.dto.author.AuthorEditRequest;
import com.example.internship.controller.dto.author.AuthorEditResponse;
import com.example.internship.dao.Author;
import com.example.internship.facade.AuthorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class BookcaseController {

    private final AuthorFacade authorFacade;

    @GetMapping
    public Iterable<Author> findAllCall() {
        return authorFacade.findAll();
    }

    @PostMapping
    public AuthorCreateResponse addOneCall(@RequestBody AuthorCreateRequest authorCreateRequest){
        return authorFacade.savePostRequest(authorCreateRequest);
    }

    @PutMapping(value = "/{id}")
    public AuthorEditResponse replaceCall(@RequestBody AuthorEditRequest authorEditRequest,
                                          @PathVariable long id) {
        return authorFacade.saveEditRequest(authorEditRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteCall(@PathVariable long id) {
        authorFacade.delete(id);
    }
}
