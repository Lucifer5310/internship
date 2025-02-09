package com.example.internship.service;

import com.example.internship.dao.Bookcase;
import com.example.internship.repository.BookcaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookcaseService {

    private final BookcaseRepository bookcaseService;

    public void delete(long id) {
        bookcaseService.deleteById(id);
    }

    public Bookcase save(Bookcase bookcase){
        return bookcaseService.save(bookcase);
    }

    public Iterable<Bookcase> findAll() {
        return bookcaseService.findAll();
    }

    public Bookcase findById(long id) {
        Optional<Bookcase> byId = bookcaseService.findById(id);
        return byId.orElseThrow();
    }
}
