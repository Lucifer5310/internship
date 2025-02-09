package com.example.internship.service;

import com.example.internship.dao.Shelf;
import com.example.internship.repository.ShelfRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShelfService {

    private final ShelfRepository shelfRepository;

    public void delete(long id) {
        shelfRepository.deleteById(id);
    }

    public Shelf save(Shelf shelf){
        return shelfRepository.save(shelf);
    }

    public Iterable<Shelf> findAll() {
        return shelfRepository.findAll();
    }

    public Shelf findById(long id) {
        Optional<Shelf> byId = shelfRepository.findById(id);
        return byId.orElseThrow();
    }
}
