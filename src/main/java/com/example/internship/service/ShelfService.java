package com.example.internship.service;

import com.example.internship.dao.entity.Book;
import com.example.internship.dao.entity.Shelf;
import com.example.internship.dao.repository.ShelfRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShelfService {

    private final ShelfRepository shelfRepository;

    @Transactional
    public void delete(Long id) {
        Optional<Shelf> shelfOptional = shelfRepository.findById(id);

        if (shelfOptional.isPresent()) {
            Shelf shelf = shelfOptional.get();

            for (Book book : shelf.getBooks()) {
                book.setShelf(null);
            }
            shelf.getBooks().clear();

            if (shelf.getBookcase() != null) {
                shelf.getBookcase().getShelfs().remove(shelf);
                shelf.setBookcase(null);
            }

            shelfRepository.save(shelf);
            shelfRepository.deleteById(id);
            log.info("Shelf is deleted");
        } else {
            throw new RuntimeException("Shelf with ID " + id + " not found");
        }
    }

    @Transactional
    public Shelf save(Shelf shelf){
        return shelfRepository.save(shelf);
    }

    @Transactional(readOnly = true)
    public Iterable<Shelf> findAll() {
        return shelfRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Shelf findByName(String name) {
        Optional<Shelf> byName = shelfRepository.findShelfByName(name);
        return byName.orElseThrow(() -> new RuntimeException("Shelf not found"));
    }


    @Transactional(readOnly = true)
    public Shelf findById(long id) {
        Optional<Shelf> byId = shelfRepository.findById(id);
        return byId.orElseThrow(() -> new RuntimeException("Shelf not found"));
    }

    @Transactional(readOnly = true)
    public List<String> findAllShelfName() {
        return shelfRepository.findAll().stream()
                .map(Shelf::getName)
                .collect(Collectors.toList());
    }
}
