package com.example.internship.facade;


import com.example.internship.controller.dto.author.AuthorCreateRequest;
import com.example.internship.controller.dto.author.AuthorCreateResponse;
import com.example.internship.controller.dto.author.AuthorEditRequest;
import com.example.internship.controller.dto.author.AuthorEditResponse;
import com.example.internship.dao.Author;
import com.example.internship.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ShelfFacade {

    private final AuthorService authorService;

    public Iterable<Author> findAll() {
        return authorService.findAll();
    }

    public void delete(long id) {
        authorService.delete((int) id);
    }

    public AuthorCreateResponse savePostRequest(AuthorCreateRequest authorCreateRequest){
        final Author author = new Author();
        author.setName(authorCreateRequest.getName());
        author.setDateOfBirth(authorCreateRequest.getDateOfBirth());

        Author saved = authorService.save(author);

        return AuthorCreateResponse.builder()
                .name(saved.getName())
                .dateOfBirth(saved.getDateOfBirth())
                .build();
    }

    public AuthorEditResponse saveEditRequest(AuthorEditRequest authorEditRequest, long id){
        Author author = authorService.findById(id);
        author.setName(authorEditRequest.getName());
        author.setDateOfBirth(authorEditRequest.getDateOfBirth());

        //System system = systemService.findById(callEditRequest.getSystemId());

        Author saved = authorService.save(author);

        return AuthorEditResponse.builder()
                .name(saved.getName())
                .dateOfBirth(saved.getDateOfBirth())
                .build();
    }
}
