package com.example.internship.facade;

import com.example.internship.dto.bookcase.BookcaseCreateRequest;
import com.example.internship.dto.bookcase.BookcaseCreateResponse;
import com.example.internship.dto.bookcase.BookcaseEditRequest;
import com.example.internship.dto.bookcase.BookcaseEditResponse;
import com.example.internship.dao.entity.Bookcase;
import com.example.internship.service.BookcaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookcaseFacade {

    private final BookcaseService bookcaseService;

    public Iterable<Bookcase> findAll() {
        return bookcaseService.findAll();
    }

    public void delete(long id) {
        bookcaseService.delete(id);
    }

    public BookcaseCreateResponse savePostRequest(BookcaseCreateRequest bookcaseCreateRequest){
        final Bookcase bookcase = new Bookcase(); // why final?
        bookcase.setNumber(bookcaseCreateRequest.getNumber());

        Bookcase saved = bookcaseService.save(bookcase);
        log.info("Bookcase is added");

        return BookcaseCreateResponse.builder()
                .number(saved.getNumber())
                .build();
    }

    public BookcaseEditResponse saveEditRequest(BookcaseEditRequest bookcaseEditRequest, long id){
        Bookcase bookcase = bookcaseService.findById(id);
        bookcase.setNumber(bookcaseEditRequest.getNumber());

        Bookcase saved = bookcaseService.save(bookcase);
        log.info("Bookcase is edited");

        return BookcaseEditResponse.builder()
                .number(saved.getNumber())
                .build();
    }
}
