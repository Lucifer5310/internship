package com.example.internship.facade;

import com.example.internship.controller.dto.bookcase.BookcaseCreateRequest;
import com.example.internship.controller.dto.bookcase.BookcaseCreateResponse;
import com.example.internship.controller.dto.bookcase.BookcaseEditRequest;
import com.example.internship.controller.dto.bookcase.BookcaseEditResponse;
import com.example.internship.dao.Bookcase;
import com.example.internship.service.BookcaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookcaseFacade {

    private final BookcaseService bookcaseService;

    public Iterable<Bookcase> findAll() {
        return bookcaseService.findAll();
    }

    public void delete(long id) {
        bookcaseService.delete((int) id);
    }

    public BookcaseCreateResponse savePostRequest(BookcaseCreateRequest bookcaseCreateRequest){
        final Bookcase bookcase = new Bookcase();
        bookcase.setNumber(bookcaseCreateRequest.getNumber());

        Bookcase saved = bookcaseService.save(bookcase);

        return BookcaseCreateResponse.builder()
                .number(saved.getNumber())
                .build();
    }

    public BookcaseEditResponse saveEditRequest(BookcaseEditRequest bookcaseEditRequest, long id){
        Bookcase bookcase = bookcaseService.findById(id);
        bookcase.setNumber(bookcaseEditRequest.getNumber());

        Bookcase saved = bookcaseService.save(bookcase);

        return BookcaseEditResponse.builder()
                .number(saved.getNumber())
                .build();
    }
}
