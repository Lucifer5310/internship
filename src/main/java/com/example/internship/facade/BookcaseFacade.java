package com.example.internship.facade;

import com.example.internship.dao.entity.Shelf;
import com.example.internship.dto.bookcase.*;
import com.example.internship.dao.entity.Bookcase;
import com.example.internship.service.BookcaseService;
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
public class BookcaseFacade {

    private final BookcaseService bookcaseService;

    public Iterable<BookcaseGetResponse> findAll() {
        return StreamSupport.stream(bookcaseService.findAll().spliterator(), false)
                .map(bookcase -> new BookcaseGetResponse(
                        bookcase.getNumber(),
                        bookcase.getShelfs().stream()
                                .map(Shelf::getName)
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

    public void delete(long id) {
        bookcaseService.delete(id);
    }

    public BookcaseCreateResponse savePostRequest(BookcaseCreateRequest bookcaseCreateRequest){
        final Bookcase bookcase = new Bookcase();
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

    public ResponseEntity<List<Integer>> findAllBookcaseNumbers(){
        List<Integer> bookcaseNumbers = bookcaseService.findAllBookcaseNumbers();
        return ResponseEntity.ok(bookcaseNumbers);
    }

    public Bookcase findBookcaseById(Long id) {
        return bookcaseService.findById(id);
    }
}
