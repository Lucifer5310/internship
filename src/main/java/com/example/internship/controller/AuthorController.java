package com.example.internship.controller;

import com.example.internship.dto.author.AuthorCreateRequest;
import com.example.internship.dto.author.AuthorCreateResponse;
import com.example.internship.dto.author.AuthorEditRequest;
import com.example.internship.dto.author.AuthorEditResponse;
import com.example.internship.dao.entity.Author;
import com.example.internship.facade.AuthorFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorFacade authorFacade;

    // Return entity is bad practice, create dto
    // And better to create pagination
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
