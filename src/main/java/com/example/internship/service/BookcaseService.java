package com.example.internship.service;

import com.example.internship.dao.entity.Bookcase;
import com.example.internship.dao.entity.Shelf;
import com.example.internship.dao.repository.BookcaseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookcaseService {

    private final BookcaseRepository bookcaseRepository;

    @Transactional
    public void delete(Long id) {
        Optional<Bookcase> optionalBookcase = bookcaseRepository.findById(id);

        if (optionalBookcase.isPresent()) {
            Bookcase bookcase = optionalBookcase.get();

            for (Shelf shelf : bookcase.getShelfs()) {
                shelf.setBookcase(null);
            }
            bookcase.getShelfs().clear();

            bookcaseRepository.save(bookcase);
            bookcaseRepository.deleteById(id);
            log.info("Bookcase is deleted");
        } else {
            throw new RuntimeException("Bookcase with ID " + id + " not found");
        }
    }

    @Transactional
    public Bookcase save(Bookcase bookcase){
        return bookcaseRepository.save(bookcase);
    }

    @Transactional(readOnly = true)
    public Iterable<Bookcase> findAll() {
        return bookcaseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Bookcase findById(long id) {
        Optional<Bookcase> byId = bookcaseRepository.findById(id);
        return byId.orElseThrow(() -> new UsernameNotFoundException("Bookcase not found"));
    }

    public Bookcase findByNumber(Integer number) {
        Optional<Bookcase> byNumber = bookcaseRepository.findBookcaseByNumber(number);
        return byNumber.orElseThrow(() -> new RuntimeException("Bookcase not found"));
    }

    public List<Integer> findAllBookcaseNumbers() {
        return bookcaseRepository.findAll().stream()
                .map(Bookcase::getNumber)
                .collect(Collectors.toList());
    }
}
