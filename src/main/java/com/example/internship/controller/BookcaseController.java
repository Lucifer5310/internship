package com.example.internship.controller;


import com.example.internship.dto.bookcase.BookcaseCreateRequest;
import com.example.internship.dto.bookcase.BookcaseCreateResponse;
import com.example.internship.dto.bookcase.BookcaseEditRequest;
import com.example.internship.dto.bookcase.BookcaseEditResponse;
import com.example.internship.dao.entity.Bookcase;
import com.example.internship.facade.BookcaseFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookcase")
@RequiredArgsConstructor
public class BookcaseController {

    private final BookcaseFacade bookcaseFacade;

    @GetMapping
    public Iterable<Bookcase> findAllBookcase() {
        return bookcaseFacade.findAll();
    }

    @PostMapping
    public BookcaseCreateResponse addOneBookcase(@RequestBody BookcaseCreateRequest bookcaseCreateRequest){
        return bookcaseFacade.savePostRequest(bookcaseCreateRequest);
    }

    @PutMapping(value = "/{id}")
    public BookcaseEditResponse replaceBookcase(@RequestBody BookcaseEditRequest bookcaseEditRequest,
                                                @PathVariable long id) {
        return bookcaseFacade.saveEditRequest(bookcaseEditRequest, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookcase(@PathVariable long id) {
        bookcaseFacade.delete(id);
    }
}
