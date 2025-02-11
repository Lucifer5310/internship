package com.example.internship.service;

import com.example.internship.dao.entity.Bookcase;
import com.example.internship.dao.entity.Shelf;
import com.example.internship.dao.repository.BookcaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
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
            log.info("Bookcase is deleted");
        } else {
            throw new RuntimeException("Bookcase with ID " + id + " not found");
        }
    }

    @Transactional
    public Bookcase save(Bookcase bookcase){
        return bookcaseService.save(bookcase);
    }

    @Transactional(readOnly = true)
    public Iterable<Bookcase> findAll() {
        return bookcaseService.findAll();
    }

    @Transactional(readOnly = true)
    public Bookcase findById(long id) {
        Optional<Bookcase> byId = bookcaseService.findById(id);
        return byId.orElseThrow(() -> new UsernameNotFoundException("Bookcase not found"));
    }
}
