package com.example.internship.service;

import com.example.internship.dao.Bookcase;
import com.example.internship.dao.Shelf;
import com.example.internship.repository.BookcaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookcaseService {

    private final BookcaseRepository bookcaseService;

    @Transactional
    public void delete(Long id) {
        Optional<Bookcase> optionalBookcase = bookcaseService.findById(id);

        if (optionalBookcase.isPresent()) {
            Bookcase bookcase = optionalBookcase.get();

            for (Shelf shelf : bookcase.getShelfs()) {
                shelf.setBookcase(null);
            }
            bookcase.getShelfs().clear();

            bookcaseService.save(bookcase);
            bookcaseService.deleteById(id);
        } else {
            throw new RuntimeException("Шкаф с ID " + id + " не найден");
        }
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
