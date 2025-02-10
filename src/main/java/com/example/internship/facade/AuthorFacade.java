package com.example.internship.facade;


import com.example.internship.controller.dto.author.AuthorCreateRequest;
import com.example.internship.controller.dto.author.AuthorCreateResponse;
import com.example.internship.controller.dto.author.AuthorEditRequest;
import com.example.internship.controller.dto.author.AuthorEditResponse;
import com.example.internship.dao.Author;
import com.example.internship.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthorFacade {

    private final AuthorService authorService;

    public Iterable<Author> findAll() {
        return authorService.findAll();
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
}
