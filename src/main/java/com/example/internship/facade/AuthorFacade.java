package com.example.internship.facade;


import com.example.internship.dto.author.*;
import com.example.internship.dao.entity.Author;
import com.example.internship.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorFacade {

    private final AuthorService authorService;

    public Iterable<AuthorGetResponse> findAll() {

        return StreamSupport.stream(authorService.findAll().spliterator(), false)
                .map(author -> new AuthorGetResponse(
                        author.getId(),
                        author.getName(),
                        author.getDateOfBirth()))
                .collect(Collectors.toList());
    }

    public void delete(long id) {
        authorService.delete(id);
    }

    public AuthorCreateResponse savePostRequest(AuthorCreateRequest authorCreateRequest){
        final Author author = new Author();
        author.setName(authorCreateRequest.getName());
        author.setDateOfBirth(authorCreateRequest.getDateOfBirth());

        Author saved = authorService.save(author);
        log.info("Author is added");

        return AuthorCreateResponse.builder()
                .name(saved.getName())
                .dateOfBirth(saved.getDateOfBirth())
                .build();
    }

    public AuthorEditResponse saveEditRequest(AuthorEditRequest authorEditRequest, long id){
        Author author = authorService.findById(id);
        author.setName(authorEditRequest.getName());
        author.setDateOfBirth(authorEditRequest.getDateOfBirth());

        Author saved = authorService.save(author);
        log.info("Author is edited");

        return AuthorEditResponse.builder()
                .name(saved.getName())
                .dateOfBirth(saved.getDateOfBirth())
                .build();
    }

    public AuthorGetByIdResponse findAuthorBuId(long id) {
        Author author = authorService.findById(id);
        return AuthorGetByIdResponse.builder()
                .name(author.getName())
                .dateOfBirth(author.getDateOfBirth())
                .build();
    }

    public ResponseEntity<List<String>> findAllAuthorName() {
        List<String> names = authorService.findAllAuthorName();
        return ResponseEntity.ok(names);
    }
}
